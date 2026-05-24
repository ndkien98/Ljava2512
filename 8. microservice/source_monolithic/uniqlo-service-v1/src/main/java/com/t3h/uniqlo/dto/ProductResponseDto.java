package com.t3h.uniqlo.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String materialInfo;
    private String avatar;
    private Integer categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private List<SkuDto> skus;
    private List<ProductImageDto> images;
}
