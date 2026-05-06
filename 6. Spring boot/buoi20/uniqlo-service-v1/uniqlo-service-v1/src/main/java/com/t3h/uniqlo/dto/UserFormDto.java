package com.t3h.uniqlo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO phục vụ UI trang user_form.html.
 * Lưu ý: password/confirmPassword chỉ dùng cho UI.
 */
@Data
@Builder
public class UserFormDto {
    private Integer id;
    private String fullName;
    private String email;
    private String gender;
    private LocalDate birthday;
    private String avatar;

    /** role hiển thị ở UI form (ADMIN/USER) */
    private String role;

    private String password;
    private String confirmPassword;
}

