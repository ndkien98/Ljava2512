package com.t3h.uniqlo.service;

import com.t3h.uniqlo.dto.ProductRequestDto;
import com.t3h.uniqlo.dto.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponseDto> getAllProducts(String keyword, Integer categoryId, String createdBy, Pageable pageable);
    Page<ProductResponseDto> getAllProducts2(String keyword, Integer categoryId, String createdBy, Pageable pageable);
    ProductResponseDto getProductById(Integer id);
    ProductResponseDto createProduct(ProductRequestDto requestDto);
    ProductResponseDto updateProduct(Integer id, ProductRequestDto requestDto);
    void deleteProduct(Integer id);
}
