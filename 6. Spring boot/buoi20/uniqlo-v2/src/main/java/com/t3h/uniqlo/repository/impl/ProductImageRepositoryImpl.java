package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.ProductImage;
import com.t3h.uniqlo.repository.ProductImageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductImageRepositoryImpl extends BaseRepositoryImpl<ProductImage, Integer> implements ProductImageRepository {
}
