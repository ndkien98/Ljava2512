package com.t3h.uniqlo.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductRequestDto {
    private Integer id;
    private String name;
    private String description;
    private String materialInfo;
    private String avatar;
    private Integer categoryId;
    private List<SkuDto> skus;
    private List<ProductImageDto> images;
}
