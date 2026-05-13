package com.t3h.uniqlo.controller.resource;

import com.t3h.uniqlo.dto.UserListItemDto;
import com.t3h.uniqlo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserListItemDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers(null, null, 0, 1000));
    }
}
