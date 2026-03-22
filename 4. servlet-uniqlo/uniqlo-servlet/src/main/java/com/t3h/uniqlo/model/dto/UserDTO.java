package com.t3h.uniqlo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String  fullName;
    private String  email;
    private String  passwordHash;
    private LocalDate birthday;
    private String  gender;
    private String  role;
    private String  rememberToken;
    private String  avatar;
    private LocalDateTime createdAt;
}
