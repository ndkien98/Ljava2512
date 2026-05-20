package com.t3h.uniqlo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t3h.uniqlo.config.aop.LogUserAction;
import com.t3h.uniqlo.config.security.JwtService;
import com.t3h.uniqlo.dto.AuthenticationRequest;
import com.t3h.uniqlo.dto.AuthenticationResponse;
import com.t3h.uniqlo.entity.Token;
import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.repository.TokenRepository;
import com.t3h.uniqlo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    // Xu ly logic dang nhap cho API, tra ve Access Token va Refresh Token
    @LogUserAction("User API Login")
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // 1. Kiem tra thong tin dang nhap thong qua AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        // 2. Tim thong tin user trong Database
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
                
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        
        // 3. Tao JWT Token va Refresh Token su dung RSA Private Key
        var jwtToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        
        // 4. Thu hoi tat ca token cu (Revoke) va luu token moi vao Database
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    // Luu Token vao Database de quan ly trang thai
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    // Danh dau tat ca cac token hien tai cua User la da het han (expired) va bi thu hoi (revoked)
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser((long) user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    // Xu ly logic cap lai Access Token bang Refresh Token
    @LogUserAction("User Refresh Token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        
        if (userEmail != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtService.generateToken(userDetails);
                
                var user = repository.findByEmail(userEmail).orElseThrow();
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
