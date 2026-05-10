package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.dto.CategoryFormDto;
import com.t3h.uniqlo.dto.CategoryTreeDto;
import com.t3h.uniqlo.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cms/categories")
public class CategoryController {

    private static final String FLASH_SUCCESS = "successMessage";
    private static final String FLASH_ERROR = "errorMessage";
    private static final String REDIRECT_CATEGORIES = "redirect:/cms/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategories(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "parentId", required = false) Integer parentId,
            Model model
    ) {
        List<CategoryTreeDto> categoryTree = categoryService.getCategoryTree(keyword, parentId);
        long categoryCount = categoryService.countCategories(keyword, parentId);

        // Get all categories tree to populate the parent dropdown filter and modal select
        List<CategoryTreeDto> allCategoriesTree = categoryService.getCategoryTree(null, null);

        model.addAttribute("categoryTree", categoryTree);
        model.addAttribute("allCategoriesTree", allCategoriesTree);
        model.addAttribute("categoryCount", categoryCount);
        model.addAttribute("keyword", keyword);
        model.addAttribute("parentId", parentId);

        return "cms_categories";
    }
    
    @PostMapping("/new")
    public String createCategory(
            @ModelAttribute("category") CategoryFormDto category,
            RedirectAttributes redirect
    ) {
        try {
            categoryService.createCategory(category);
            redirect.addFlashAttribute(FLASH_SUCCESS, "Tạo danh mục thành công");
        } catch (Exception e) {
            redirect.addFlashAttribute(FLASH_ERROR, e.getMessage());
        }
        return REDIRECT_CATEGORIES;
    }

    @PostMapping("/{id}/edit")
    public String updateCategory(
            @PathVariable("id") Integer id,
            @ModelAttribute("category") CategoryFormDto category,
            RedirectAttributes redirect
    ) {
        try {
            categoryService.updateCategory(id, category);
            redirect.addFlashAttribute(FLASH_SUCCESS, "Cập nhật danh mục thành công");
        } catch (Exception e) {
            redirect.addFlashAttribute(FLASH_ERROR, e.getMessage());
        }
        return REDIRECT_CATEGORIES;
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam("id") Integer id, RedirectAttributes redirect) {
        try {
            categoryService.deleteCategory(id);
            redirect.addFlashAttribute(FLASH_SUCCESS, "Đã xoá danh mục");
        } catch (Exception e) {
            redirect.addFlashAttribute(FLASH_ERROR, e.getMessage());
        }
        return REDIRECT_CATEGORIES;
    }
}
