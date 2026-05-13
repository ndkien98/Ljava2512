package com.t3h.uniqlo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SkuDto {
    private Integer id;
    private Integer colorId;
    private String colorCode;
    private Integer sizeId;
    private String sizeCode;
    private String skuCode;
    private BigDecimal originalPrice;
    private BigDecimal salePrice;
    private Integer stockQuantity;
}
