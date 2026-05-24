package com.t3h.uniqlo.dto;

import lombok.Data;

@Data
public class ProductImageDto {
    private Integer id;
    private Integer colorId;
    private String imageUrl;
    private Boolean isMain;
    private Integer sortOrder;
}
