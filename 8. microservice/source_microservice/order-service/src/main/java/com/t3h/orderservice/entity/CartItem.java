package com.t3h.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "sku_id", nullable = false)
    private Integer skuId;

    @Column(nullable = false)
    private Integer quantity;
}
