package com.t3h.uniqlo.controller.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @GetMapping()
    public String getAllUsers() {
        return "List of all users";
    }
}
