package com.t3h.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * User Service Application
 *
 * @EnableDiscoveryClient: kích hoạt Eureka Client
 * - Khi khởi động, service này tự đăng ký với Eureka Server tại localhost:8080
 * - Tên đăng ký = spring.application.name = "user-service"
 * - Các service khác có thể gọi service này qua: http://USER-SERVICE/api/...
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
