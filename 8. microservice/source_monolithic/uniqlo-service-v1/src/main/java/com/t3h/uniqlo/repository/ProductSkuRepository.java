package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductSkuRepository extends JpaRepository<ProductSku, Integer> {
    List<ProductSku> findByProductId(Integer productId);
    void deleteByProductId(Integer productId);
}
