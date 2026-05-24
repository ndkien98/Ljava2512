package com.t3h.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_skus")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSku extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Lưu ID thay vì join entity – tránh phụ thuộc cross-service
    @Column(name = "color_id", nullable = false)
    private Integer colorId;

    @Column(name = "size_id", nullable = false)
    private Integer sizeId;

    @Column(name = "sku_code", unique = true, length = 100)
    private String skuCode;

    @Column(name = "original_price", nullable = false)
    private BigDecimal originalPrice;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "stock_quantity")
    private Integer stockQuantity = 0;
}
