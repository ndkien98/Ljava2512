package com.uniqlo.model;

import lombok.*;
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
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
