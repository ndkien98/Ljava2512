package com.t3h.orderservice.repository;

import com.t3h.orderservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUserIdAndDeleted(Integer userId, Byte deleted);
}
