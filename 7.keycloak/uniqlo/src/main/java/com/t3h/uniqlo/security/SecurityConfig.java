package com.t3h.uniqlo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * SecurityConfig - Cấu hình Spring Security 6+ cho Resource Server
 *
 * Nguyên tắc hoạt động:
 * 1. Ứng dụng hoạt động Stateless (không có session), mỗi request phải kèm JWT.
 * 2. JWT được Keycloak ký, Spring Boot dùng Public Key của Keycloak để xác
 * minh.
 * 3. KeycloakRoleConverter sẽ đọc "realm_access.roles" từ JWT và tạo
 * GrantedAuthority.
 * 4. @PreAuthorize trên Controller sẽ hoạt động nhờ @EnableMethodSecurity.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Kích hoạt @PreAuthorize, @PostAuthorize, @Secured
@RequiredArgsConstructor
public class SecurityConfig {

    private final KeycloakRoleConverter keycloakRoleConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 1. Tắt CSRF (không cần thiết với REST API Stateless)
        http.csrf(AbstractHttpConfigurer::disable);

        // 2. Cấu hình CORS: Cho phép frontend gọi API từ localhost:3000
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            // Chỉ cho phép các origin đã được whitelist (không dùng "*" khi có
            // allowCredentials)
            config.setAllowedOrigins(List.of(
                    "http://localhost:3000", // React / Vue / Angular dev server
                    "http://localhost:8082" // Swagger UI tự gọi
            ));
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
            // QUAN TRỌNG: Phải có "Authorization" để frontend gửi được Bearer token
            config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
            config.setAllowCredentials(true);
            config.setMaxAge(3600L); // Cache preflight request 1 giờ
            return config;
        }));

        // 3. Không dùng HTTP Session (Stateless)
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 4. Phân quyền các endpoint
        http.authorizeHttpRequests(auth -> auth
                // Public endpoints: Swagger, health check
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/actuator/health")
                .permitAll()

                // Tất cả các endpoint còn lại yêu cầu xác thực
                .anyRequest().authenticated());

        // 5. Khai báo đây là Resource Server, xác thực bằng JWT
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->
        // Sử dụng KeycloakRoleConverter để đọc roles từ cấu trúc Keycloak
        jwt.jwtAuthenticationConverter(keycloakRoleConverter)));

        return http.build();
    }
}
