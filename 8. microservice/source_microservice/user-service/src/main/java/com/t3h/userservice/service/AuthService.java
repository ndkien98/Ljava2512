package com.t3h.userservice.service;

import com.t3h.userservice.dto.AuthRequest;
import com.t3h.userservice.dto.AuthResponse;
import com.t3h.userservice.entity.Token;
import com.t3h.userservice.entity.User;
import com.t3h.userservice.repository.TokenRepository;
import com.t3h.userservice.repository.UserRepository;
import com.t3h.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AuthService – xử lý đăng nhập và refresh token
 *
 * Luồng đăng nhập:
 * 1. Nhận email + password từ request
 * 2. Dùng AuthenticationManager để xác thực (so sánh với DB)
 * 3. Nếu đúng → tạo access_token + refresh_token
 * 4. Lưu refresh_token vào bảng tokens
 * 5. Trả về cả 2 token cho client
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Transactional
    public AuthResponse authenticate(AuthRequest request) {
        // Bước 1: Spring Security xác thực email + password
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Bước 2: Load user từ DB
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        // Bước 3: Tạo tokens
        String accessToken = jwtService.generateAccessToken(userDetails, user.getId());
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        // Bước 4: Revoke tất cả token cũ của user, lưu token mới
        revokeAllUserTokens(user);
        saveUserToken(user, refreshToken);

        return AuthResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .email(user.getEmail())
            .fullName(user.getFullName())
            .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType("BEARER")
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
