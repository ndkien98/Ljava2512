package vn.edu.t3h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.t3h.dto.request.UserCreateRequest;
import vn.edu.t3h.dto.request.UserUpdateRequest;
import vn.edu.t3h.exception.NotFoundException;
import vn.edu.t3h.exception.ValidationException;
import vn.edu.t3h.model.User;
import vn.edu.t3h.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(
            @RequestParam(value = "keyword", required = false) String keyword,// required = false: tham số này không bắt buộc truyền nên server
            @RequestParam(value = "role", required = false) String role,
            Model model
    ) {
        model.addAttribute("users", userService.getUsers(keyword, role));
        model.addAttribute("keyword", keyword);
        model.addAttribute("role", role);
        return "admin/user_list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("mode", "create");
        return "admin/user_form";
    }

    @PostMapping("/create")
    public String createSubmit(@ModelAttribute UserCreateRequest request, RedirectAttributes ra, Model model) {
        try {
            userService.createUser(request);
            ra.addFlashAttribute("successMessage", "Created user successfully");
            return "redirect:/admin/users";
        } catch (ValidationException e) {
            model.addAttribute("mode", "create");
            model.addAttribute("errors", e.getErrors());
            model.addAttribute("user", request);
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/user_form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.getUserById(id);
            model.addAttribute("mode", "edit");
            model.addAttribute("user", user);
            return "admin/user_form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/{id}/edit")
    public String editSubmit(@PathVariable("id") Integer id, @ModelAttribute UserUpdateRequest request, RedirectAttributes ra, Model model) {
        request.setId(id);
        try {
            userService.updateUser(request);
            ra.addFlashAttribute("successMessage", "Updated user successfully");
            return "redirect:/admin/users";
        } catch (ValidationException e) {
            model.addAttribute("mode", "edit");
            model.addAttribute("errors", e.getErrors());
            model.addAttribute("user", request);
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/user_form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            userService.deleteUser(id);
            ra.addFlashAttribute("successMessage", "Deleted user successfully");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/users";
    }
}
