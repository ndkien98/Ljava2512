package com.t3h.uniqlo.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomUserDetailsService userDetailsService;
    private final LogoutHandler logoutHandler;

    // Bean de ma hoa mat khau truoc khi luu vao DB va de kiem tra mat khau luc login
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean cung cap logic xac thuc nguoi dung, su dung UserDetailsService va PasswordEncoder
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Bean quan ly viec xac thuc (Authentication Manager)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 1. API Security Filter Chain (Stateless, uses JWT)
    // Co che nay uu tien so 1 (@Order(1)), chi ap dung cho cac URL bat dau bang /api/**
    // Khong su dung Session (STATELESS), su dung JwtAuthenticationFilter de kiem tra Token
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/**") // Chi bat cac request bat dau bang /api/
            .csrf(csrf -> csrf.disable()) // Tat CSRF vi API stateless khong de bi tan cong CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll() // Cho phep public cac URL dang nhap/refresh
                .anyRequest().authenticated() // Cac API khac phai co JWT hop le
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.NEVER)) // Khong tao session moi, nhung van doc session co san (ho tro form login goi API)
            .authenticationProvider(authenticationProvider()) // Cung cap co che xac thuc
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Them filter JWT vao truoc filter dang nhap mac dinh
            .logout(logout -> logout
                .logoutUrl("/api/v1/auth/logout") // API de dang xuat
                .addLogoutHandler(logoutHandler) // Xu ly thu hoi token
                .logoutSuccessHandler((request, response, authentication) -> {
                    SecurityContextHolder.clearContext();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"message\":\"Logout successful\"}");
                })
            );

        return http.build();
    }

    // 2. Web Security Filter Chain (Stateful, uses Form Login/Session)
    // Co che nay uu tien so 2 (@Order(2)), ap dung cho tat ca cac URL con lai (Giao dien Web)
    // Su dung Session JSESSIONID mac dinh cua Spring Security
    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tat CSRF de thuan tien cho muc dich demo
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll() // Public cac tai nguyen tinh va trang login
                .anyRequest().authenticated() // Cac trang khac yeu cau dang nhap
            )
            .formLogin(form -> form
                .loginPage("/login") // Chi dinh trang login custom
                .usernameParameter("email") // Mapping voi <input name="email"> trong login.html
                .defaultSuccessUrl("/cms", true) // Chuyen huong ve /cms sau khi login thanh cong
                .failureUrl("/login?error=true") // Chuyen huong khi login that bai
                .permitAll()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Cho phep Web UI nhan dien JWT tu Cookie
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // Cho phep GET request logout (vi CSRF da tat)
                .addLogoutHandler(logoutHandler) // Thu hoi JWT token neu co
                .invalidateHttpSession(true) // Huy session
                .deleteCookies("JSESSIONID", "jwt_token") // Xoa cookie
                .logoutSuccessUrl("/login?logout=true") // Chuyen huong ve trang login sau khi dang xuat
                .permitAll()
            );

        return http.build();
    }
}
