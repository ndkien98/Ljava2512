package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.Category;
import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    Category findById(Integer id);
    Category save(Category category);
    int update(Category category);
    int deleteById(Integer id);
}
