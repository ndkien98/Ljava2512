package com.t3h.uniqlo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public String getUsers() {
        return "user-list";
    }

}
