package com.t3h.uniqlo.dto.response;

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
public class UserResponse {
    private Integer id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String gender;
    private String role;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
