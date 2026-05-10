package com.t3h.uniqlo.mapper;

import com.t3h.uniqlo.dto.CategoryFormDto;
import com.t3h.uniqlo.dto.CategoryListItemDto;
import com.t3h.uniqlo.dto.CategoryTreeDto;
import com.t3h.uniqlo.entity.Category;

import java.util.ArrayList;

public class CategoryMapper {

    public static CategoryListItemDto toListItemDto(Category category) {
        if (category == null) return null;
        
        return CategoryListItemDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .parentName(category.getParent() != null ? category.getParent().getName() : null)
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .build();
    }

    public static CategoryTreeDto toTreeDto(Category category) {
        if (category == null) return null;

        return CategoryTreeDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .parentName(category.getParent() != null ? category.getParent().getName() : null)
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .children(new ArrayList<>())
                .build();
    }

    public static CategoryFormDto toFormDto(Category category) {
        if (category == null) return null;
        
        return CategoryFormDto.builder()
                .name(category.getName())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .build();
    }
}
