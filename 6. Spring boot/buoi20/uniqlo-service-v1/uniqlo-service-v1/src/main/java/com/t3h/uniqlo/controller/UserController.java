package com.t3h.uniqlo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public String getUsers() {
        // maps to src/main/resources/templates/user_list.html
        return "user_list";
    }

}
