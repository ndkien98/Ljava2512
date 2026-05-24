package com.t3h.orderservice.controller;

import com.t3h.orderservice.dto.CartItemRequest;
import com.t3h.orderservice.dto.CartItemResponse;
import com.t3h.orderservice.entity.CartItem;
import com.t3h.orderservice.repository.CartItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartItemRepository cartItemRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponse>> getCart(@PathVariable Integer userId) {
        List<CartItem> items = cartItemRepository.findByUserIdAndDeleted(userId, (byte) 0);
        List<CartItemResponse> response = items.stream()
            .map(i -> CartItemResponse.builder()
                .id(i.getId())
                .userId(i.getUserId())
                .skuId(i.getSkuId())
                .quantity(i.getQuantity())
                .build())
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CartItemResponse> addToCart(@RequestBody CartItemRequest request) {
        CartItem item = CartItem.builder()
            .userId(request.getUserId())
            .skuId(request.getSkuId())
            .quantity(request.getQuantity())
            .build();
        item.setDeleted((byte) 0);
        CartItem saved = cartItemRepository.save(item);
        return ResponseEntity.ok(CartItemResponse.builder()
            .id(saved.getId())
            .userId(saved.getUserId())
            .skuId(saved.getSkuId())
            .quantity(saved.getQuantity())
            .build());
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Integer itemId) {
        cartItemRepository.findById(itemId).ifPresent(item -> {
            item.setDeleted((byte) 1);
            cartItemRepository.save(item);
        });
        return ResponseEntity.noContent().build();
    }
}
