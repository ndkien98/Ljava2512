package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.CartItem;
import com.t3h.uniqlo.repository.CartItemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemRepositoryImpl extends BaseRepositoryImpl<CartItem, Integer> implements CartItemRepository {
}
