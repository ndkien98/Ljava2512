package com.t3h.uniqlo.controller.admin;

import com.t3h.uniqlo.dto.request.CategoryRequest;
import com.t3h.uniqlo.dto.response.CategoryResponse;
import com.t3h.uniqlo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryService categoryService;

    @GetMapping
    public String viewCategories(Model model) {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        // Bind object for the creation form
        model.addAttribute("categoryRequest", new CategoryRequest());
        return "cms/categories";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("categoryRequest") CategoryRequest request, RedirectAttributes redirectAttributes) {
        categoryService.createCategory(request);
        redirectAttributes.addFlashAttribute("successMessage", "Category created successfully!");
        return "redirect:/admin/categories";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Integer id, @ModelAttribute("categoryRequest") CategoryRequest request, RedirectAttributes redirectAttributes) {
        categoryService.updateCategory(id, request);
        redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully!");
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategory(id);
            redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/categories";
    }
}
