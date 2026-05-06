package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.dto.UserFormDto;
import com.t3h.uniqlo.dto.UserListItemDto;
import com.t3h.uniqlo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final String FLASH_SUCCESS = "successMessage";
    private static final String FLASH_ERROR = "errorMessage";
    private static final String REDIRECT_USERS = "redirect:/users";
    private static final String REDIRECT_USERS_NEW = "redirect:/users/new";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "role", required = false) String role,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            Model model
    ) {
        List<UserListItemDto> users = userService.getUsers(keyword, role, page, size);
        long userCount = userService.countUsers(keyword, role);
        int totalPages = (int) Math.ceil(userCount / (double) Math.max(size, 1));

        model.addAttribute("users", users);
        model.addAttribute("userCount", userCount);
        model.addAttribute("keyword", keyword);
        model.addAttribute("role", role);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);

        return "user_list";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", UserFormDto.builder().role("USER").build());
        return "user_form";
    }

    @PostMapping("/new")
    public String createUser(
            @ModelAttribute("user") UserFormDto user,
            RedirectAttributes redirect
    ) {
        try {
            userService.createUser(user);
            redirect.addFlashAttribute(FLASH_SUCCESS, "Tạo người dùng thành công");
            return REDIRECT_USERS;
        } catch (Exception e) {
            redirect.addFlashAttribute(FLASH_ERROR, e.getMessage());
            return REDIRECT_USERS_NEW;
        }
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
        try {
            UserFormDto user = userService.getUserForm(id);
            model.addAttribute("user", user);
            return "user_form";
        } catch (Exception e) {
            redirect.addFlashAttribute(FLASH_ERROR, e.getMessage());
            return REDIRECT_USERS;
        }
    }

    @PostMapping("/{id}/edit")
    public String updateUser(
            @PathVariable("id") Integer id,
            @ModelAttribute("user") UserFormDto user,
            RedirectAttributes redirect
    ) {
        try {
            userService.updateUser(id, user);
            redirect.addFlashAttribute(FLASH_SUCCESS, "Cập nhật người dùng thành công");
            return REDIRECT_USERS;
        } catch (Exception e) {
            redirect.addFlashAttribute(FLASH_ERROR, e.getMessage());
            return "redirect:/users/" + id + "/edit";
        }
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id, RedirectAttributes redirect) {
        try {
            userService.deleteUser(id);
            redirect.addFlashAttribute(FLASH_SUCCESS, "Đã xoá người dùng");
        } catch (Exception e) {
            redirect.addFlashAttribute(FLASH_ERROR, e.getMessage());
        }
        return REDIRECT_USERS;
    }
}
