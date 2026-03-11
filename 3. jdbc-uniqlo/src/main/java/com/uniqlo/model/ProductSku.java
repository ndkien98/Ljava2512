package com.uniqlo.model;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSku {
    private Integer id;
    private Integer productId;
    private Integer colorId;
    private Integer sizeId;
    private String skuCode;
    private BigDecimal originalPrice;
    private BigDecimal salePrice;
    private Integer stockQuantity;
    private LocalDateTime createdAt;
    private Integer createdBy;
}
