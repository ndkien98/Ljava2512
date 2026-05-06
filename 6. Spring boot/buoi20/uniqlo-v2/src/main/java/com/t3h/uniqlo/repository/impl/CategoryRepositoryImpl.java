package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Category;
import com.t3h.uniqlo.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category, Integer> implements CategoryRepository {
}
