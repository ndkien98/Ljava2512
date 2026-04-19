package com.t3h.uniqlo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Cấu hình BCrypt password encoder.
 * Sử dụng spring-security-crypto (không cần full Spring Security).
 * Đăng ký là @Bean để inject vào Service.
 */
@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // strength=12: cân bằng giữa bảo mật và hiệu năng
        return new BCryptPasswordEncoder(12);
    }
}
