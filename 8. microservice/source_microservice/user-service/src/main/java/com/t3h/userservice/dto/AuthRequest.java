package com.t3h.userservice.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
