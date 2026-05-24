package com.t3h.productservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfig – cấu hình RestTemplate để giao tiếp giữa các service
 *
 * @LoadBalanced: annotation quan trọng!
 * - Khi thêm @LoadBalanced, RestTemplate có thể dùng tên service (VD: http://MASTER-DATA-SERVICE/...)
 *   thay vì IP:Port cứng
 * - Spring Cloud LoadBalancer sẽ hỏi Eureka để lấy địa chỉ thực của service đó
 * - Nếu có nhiều instance → tự động round-robin load balancing
 *
 * Ví dụ:
 * restTemplate.getForObject("http://MASTER-DATA-SERVICE/api/colors", List.class)
 * → Spring hỏi Eureka: MASTER-DATA-SERVICE đang ở đâu?
 * → Eureka: 127.0.0.1:8083
 * → RestTemplate gọi đến 127.0.0.1:8083/api/colors
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
