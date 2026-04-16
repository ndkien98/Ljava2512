package com.t3h.uniqlo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthUiController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * UI-only fake login: link/button on login page will redirect here.
     */
    @GetMapping("/fake-login")
    public String fakeLoginRedirect() {
        return "redirect:/cms";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}

