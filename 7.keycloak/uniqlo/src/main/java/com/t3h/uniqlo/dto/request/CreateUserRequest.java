package com.t3h.uniqlo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String fullName;
    private String email;
    private String password;
    private LocalDate birthday;
    private String gender;
    private String role;
}
