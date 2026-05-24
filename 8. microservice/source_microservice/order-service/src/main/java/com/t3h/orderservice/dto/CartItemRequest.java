package com.t3h.orderservice.dto;

import lombok.Data;

@Data
public class CartItemRequest {
    private Integer userId;
    private Integer skuId;
    private Integer quantity;
}
