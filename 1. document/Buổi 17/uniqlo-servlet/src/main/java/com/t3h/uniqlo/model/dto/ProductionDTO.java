package com.t3h.uniqlo.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class ProductionDTO {

    private String id;
    private String name;
    private String description;
    private String materialInfo;
    private BigDecimal salePrice;
    private String imageUrl;
    private String categoryName;
    private Integer categoryId;

}
