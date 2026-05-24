package com.t3h.userservice.controller;

import com.t3h.userservice.dto.AuthRequest;
import com.t3h.userservice.dto.AuthResponse;
import com.t3h.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController – xử lý đăng nhập
 *
 * URL: POST /api/v1/auth/login
 * → Request body: { "email": "...", "password": "..." }
 * → Response:     { "access_token": "...", "refresh_token": "...", "email": "...", "fullName": "..." }
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
