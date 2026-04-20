package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.Category;

public interface CategoryRepository {

    Category save(Category category);

    int delete(Category category);
}
