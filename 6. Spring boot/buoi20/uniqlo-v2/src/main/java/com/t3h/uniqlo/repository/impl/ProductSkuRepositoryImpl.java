package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.ProductSku;
import com.t3h.uniqlo.repository.ProductSkuRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductSkuRepositoryImpl extends BaseRepositoryImpl<ProductSku, Integer> implements ProductSkuRepository {
}
