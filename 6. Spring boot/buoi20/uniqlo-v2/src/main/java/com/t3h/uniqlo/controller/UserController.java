package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.dto.PageDto;
import com.t3h.uniqlo.dto.UserDto;
import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String listUsers(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        int pageSize = 10;
        int pageIndex = Math.max(0, page - 1);
        PageDto<User> pageDto = userService.findAll(keyword, role, pageIndex, pageSize);

        model.addAttribute("users", pageDto.getContent());
        model.addAttribute("page", pageDto);
        model.addAttribute("userCount", pageDto.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("role", role);

        return "user_list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user_form";
    }

    @PostMapping("/new")
    public String create(
            @Valid @ModelAttribute("user") UserDto dto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Validate password (required khi tạo mới)
        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            result.rejectValue("password", "error.user", "Mật khẩu phải từ 6 ký tự trở lên");
        }
        if (dto.getPassword() != null && !dto.getPassword().equals(dto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.user", "Mật khẩu không khớp");
        }

        if (result.hasErrors()) {
            return "user_form";
        }

        try {
            userService.create(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo người dùng thành công");
            return "redirect:/users";
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.user", e.getMessage());
            return "user_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findById(id);
            model.addAttribute("user", userService.toDto(user));
            return "user_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/users";
        }
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Integer id,
            @Valid @ModelAttribute("user") UserDto dto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Validate password khi có nhập mật khẩu mới
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            if (dto.getPassword().length() < 6) {
                result.rejectValue("password", "error.user", "Mật khẩu phải từ 6 ký tự trở lên");
            }
            if (!dto.getPassword().equals(dto.getConfirmPassword())) {
                result.rejectValue("confirmPassword", "error.user", "Mật khẩu không khớp");
            }
        }

        if (result.hasErrors()) {
            dto.setId(id); // Giữ ID để template biết là form Edit
            return "user_form";
        }

        try {
            userService.update(id, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật người dùng thành công");
            return "redirect:/users";
        } catch (IllegalArgumentException e) {
            dto.setId(id);
            result.rejectValue("email", "error.user", e.getMessage());
            return "user_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/users";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xoá người dùng thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xoá: " + e.getMessage());
        }
        return "redirect:/users";
    }
}
