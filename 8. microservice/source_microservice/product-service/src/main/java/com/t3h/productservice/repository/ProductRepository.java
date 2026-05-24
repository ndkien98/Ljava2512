package com.t3h.productservice.repository;

import com.t3h.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("""
        SELECT p FROM Product p
        WHERE p.deleted = 0
          AND (:keyword IS NULL OR p.name LIKE :keyword)
          AND (:categoryId IS NULL OR p.categoryId = :categoryId)
        """)
    Page<Product> searchProducts(
        @Param("keyword") String keyword,
        @Param("categoryId") Integer categoryId,
        Pageable pageable
    );
}
