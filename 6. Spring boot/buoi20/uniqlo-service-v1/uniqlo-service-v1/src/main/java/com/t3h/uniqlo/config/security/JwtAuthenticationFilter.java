package com.t3h.uniqlo.config.security;

import com.t3h.uniqlo.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.Cookie;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Bo qua viec kiem tra JWT doi voi cac endpoint thuoc ve xac thuc (login, refresh)
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String jwt = null;
        final String userEmail;
        
        // 1. Thu lay token tu Header
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
        } else if (request.getCookies() != null) {
            // 2. Neu Header khong co, thu lay tu Cookie (De support JWT tren Web UI)
            for (Cookie cookie : request.getCookies()) {
                if ("jwt_token".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
        
        // Neu khong co token o ca Header lan Cookie, bo qua filter nay
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // Giai ma token bang Public Key de lay email (subject)
        userEmail = jwtService.extractUsername(jwt);
        
        // Kiem tra neu email ton tai va nguoi dung chua xac thuc trong Context
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            
            // Kiem tra Token trong Database xem co bi thu hoi hay het han khong
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
                    
            // Neu ca token hop le ve mat chu ky (RSA) lan trang thai trong DB
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                // Tao doi tuong xac thuc cho Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Set trang thai da xac thuc vao SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Cho phep request tiep luc di tiep vao Controller (neu da xac thuc) hoac bi block (neu chua xac thuc)
        filterChain.doFilter(request, response);
    }
}
