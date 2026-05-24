package com.t3h.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String materialInfo;
    private String avatar;
    private Integer categoryId;
    private String categoryName;   // Lấy từ master-data-service
    private List<SkuDto> skus;
    private List<ImageDto> images;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkuDto {
        private Integer id;
        private Integer colorId;
        private String colorCode;    // Lấy từ master-data-service
        private Integer sizeId;
        private String sizeCode;     // Lấy từ master-data-service
        private String skuCode;
        private BigDecimal originalPrice;
        private BigDecimal salePrice;
        private Integer stockQuantity;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageDto {
        private Integer id;
        private Integer colorId;
        private String imageUrl;
        private Boolean isMain;
        private Integer sortOrder;
    }
}
