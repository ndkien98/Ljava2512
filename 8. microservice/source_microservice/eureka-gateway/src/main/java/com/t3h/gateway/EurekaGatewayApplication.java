package com.t3h.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Eureka Gateway Application
 *
 * Đây là entry point kết hợp 2 vai trò:
 * 1. @EnableEurekaServer → Là trung tâm đăng ký (Service Registry)
 *    - Tất cả microservice tự đăng ký địa chỉ vào đây khi khởi động
 *    - Dashboard xem trạng thái: http://localhost:8080
 *
 * 2. Spring Cloud Gateway (khai báo trong application.yml)
 *    - Nhận tất cả request từ frontend
 *    - Định tuyến (route) đến service phù hợp dựa trên path
 *    - Xác thực JWT token tập trung
 */
@SpringBootApplication
public class EurekaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaGatewayApplication.class, args);
    }
}
