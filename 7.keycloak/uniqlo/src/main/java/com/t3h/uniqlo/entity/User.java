package com.t3h.uniqlo.entity;

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
public class User {
    private Integer id;
    private String fullName;
    private String email;
    private String passwordHash;
    private LocalDate birthday;
    private String gender; // Male, Female, Decline to state
    private String role; // USER, ADMIN
    private String rememberToken;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
