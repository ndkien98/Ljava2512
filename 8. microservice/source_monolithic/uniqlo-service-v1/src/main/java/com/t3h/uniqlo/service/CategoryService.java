package com.t3h.uniqlo.service;

import com.t3h.uniqlo.dto.CategoryFormDto;
import com.t3h.uniqlo.dto.CategoryListItemDto;
import com.t3h.uniqlo.dto.CategoryTreeDto;

import java.util.List;

public interface CategoryService {

    List<CategoryListItemDto> getCategories(String keyword, Integer parentId, int page, int size);

    long countCategories(String keyword, Integer parentId);

    List<CategoryTreeDto> getCategoryTree(String keyword, Integer parentId);

    CategoryFormDto getCategoryForm(Integer id);

    void createCategory(CategoryFormDto dto);

    void updateCategory(Integer id, CategoryFormDto dto);

    void deleteCategory(Integer id);
}
