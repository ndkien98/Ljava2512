package com.t3h.uniqlo.controller.resource;

import com.t3h.uniqlo.dto.CategoryListItemDto;
import com.t3h.uniqlo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryListItemDto>> getAllCategories() {
        // Return a flat list of categories for the dropdown
        // Using large size to get "all" for now, or just use the service method if it supports it
        return ResponseEntity.ok(categoryService.getCategories(null, null, 0, 1000));
    }
}
