package com.t3h.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private String materialInfo;
    private String avatar;
    private Integer categoryId;
    private List<SkuRequest> skus;
    private List<ImageRequest> images;

    @Data
    public static class SkuRequest {
        private Integer colorId;
        private Integer sizeId;
        private String skuCode;
        private BigDecimal originalPrice;
        private BigDecimal salePrice;
        private Integer stockQuantity;
    }

    @Data
    public static class ImageRequest {
        private Integer colorId;
        private String imageUrl;
        private Boolean isMain;
        private Integer sortOrder;
    }
}
