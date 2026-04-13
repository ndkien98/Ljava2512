package com.t3h.uniqlo.service.impl;

import com.t3h.uniqlo.constant.ErrorCodes;
import com.t3h.uniqlo.dto.request.CategoryRequest;
import com.t3h.uniqlo.dto.response.CategoryResponse;
import com.t3h.uniqlo.entity.Category;
import com.t3h.uniqlo.exception.ResourceNotFoundException;
import com.t3h.uniqlo.repository.CategoryRepository;
import com.t3h.uniqlo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id);
        if (category == null) {
            throw new ResourceNotFoundException(ErrorCodes.CATEGORY_NOT_FOUND, "Category not found: " + id);
        }
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .parentId(request.getParentId())
                .createdAt(LocalDateTime.now())
                .build();
        
        Category saved = categoryRepository.save(category);
        log.info("Created category: {}", saved.getId());
        return mapToResponse(saved);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id);
        if (category == null) {
            throw new ResourceNotFoundException(ErrorCodes.CATEGORY_NOT_FOUND, "Category not found: " + id);
        }

        category.setName(request.getName());
        category.setParentId(request.getParentId());
        category.setUpdatedAt(LocalDateTime.now());
        
        categoryRepository.update(category);
        return mapToResponse(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        int result = categoryRepository.deleteById(id);
        if (result == 0) {
            throw new ResourceNotFoundException(ErrorCodes.CATEGORY_NOT_FOUND, "Category not found: " + id);
        }
    }

    private CategoryResponse mapToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParentId())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
