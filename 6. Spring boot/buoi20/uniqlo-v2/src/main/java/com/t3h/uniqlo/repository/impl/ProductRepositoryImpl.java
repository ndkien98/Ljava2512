package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Product;
import com.t3h.uniqlo.repository.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl extends BaseRepositoryImpl<Product, Integer> implements ProductRepository {
}
