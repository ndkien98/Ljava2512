package com.t3h.uniqlo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO phục vụ UI trang user_list.html.
 */
@Data
@Builder
public class UserListItemDto {
    private Integer id;
    private String fullName;
    private String email;
    private String gender;
    private LocalDate birthday;
    private String avatar;

    /** role hiển thị ở UI list (ADMIN/USER) */
    private String role;

    private LocalDateTime createdAt;
}

