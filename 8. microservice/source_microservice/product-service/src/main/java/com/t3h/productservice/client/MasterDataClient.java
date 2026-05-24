package com.t3h.productservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MasterDataClient – gọi sang master-data-service qua @LoadBalanced RestTemplate
 *
 * Đây là ví dụ điển hình về Service-to-Service Communication trong Microservice
 *
 * Luồng:
 * 1. product-service cần lấy color/size info
 * 2. Gọi restTemplate.getForObject("http://MASTER-DATA-SERVICE/api/colors", ...)
 * 3. Spring Cloud LoadBalancer hỏi Eureka: MASTER-DATA-SERVICE đang ở đâu?
 * 4. Eureka trả về: 127.0.0.1:8083
 * 5. RestTemplate gọi đến 127.0.0.1:8083/api/colors
 */
@Component
@RequiredArgsConstructor
public class MasterDataClient {

    // RestTemplate này được annotate @LoadBalanced trong RestTemplateConfig
    private final RestTemplate restTemplate;

    // Tên service đăng ký với Eureka (viết hoa)
    private static final String MASTER_DATA_SERVICE = "http://MASTER-DATA-SERVICE";

    /**
     * Lấy map colorId → colorCode từ master-data-service
     */
    public Map<Integer, String> getColorMap() {
        try {
            List<ColorDto> colors = restTemplate.exchange(
                MASTER_DATA_SERVICE + "/api/colors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ColorDto>>() {}
            ).getBody();

            if (colors == null) return Collections.emptyMap();
            return colors.stream()
                .collect(Collectors.toMap(ColorDto::getId, ColorDto::getColorCode));
        } catch (Exception e) {
            // Nếu master-data-service down → trả về map rỗng thay vì crash
            return Collections.emptyMap();
        }
    }

    /**
     * Lấy map sizeId → sizeCode từ master-data-service
     */
    public Map<Integer, String> getSizeMap() {
        try {
            List<SizeDto> sizes = restTemplate.exchange(
                MASTER_DATA_SERVICE + "/api/sizes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SizeDto>>() {}
            ).getBody();

            if (sizes == null) return Collections.emptyMap();
            return sizes.stream()
                .collect(Collectors.toMap(SizeDto::getId, SizeDto::getSizeCode));
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    // DTO nội bộ để parse response từ master-data-service
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ColorDto {
        private Integer id;
        private String colorCode;
        private String hexCode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SizeDto {
        private Integer id;
        private String sizeCode;
    }
}
