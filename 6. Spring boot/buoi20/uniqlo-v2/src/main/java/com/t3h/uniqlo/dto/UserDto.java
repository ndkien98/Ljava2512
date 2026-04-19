package com.t3h.uniqlo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO dùng cho form Create / Edit user.
 * Tách biệt khỏi Entity để không expose password_hash ra ngoài.
 */
@Data
public class UserDto {

    /** Không dùng khi tạo mới (null), có giá trị khi edit */
    private Integer id;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên tối đa 100 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 255)
    private String email;

    /**
     * Mật khẩu: bắt buộc khi tạo mới, tuỳ chọn khi sửa.
     * Validation phụ hơn được xử lý trong service/controller.
     */
    @Size(min = 6, max = 100, message = "Mật khẩu phải từ 6–100 ký tự")
    private String password;

    private String confirmPassword;

    private LocalDate birthday;

    /** Giá trị: 'Male' | 'Female' | 'Decline to state' */
    private String gender;

    /** Giá trị: 'USER' | 'ADMIN' */
    private String role = "USER";

    private String avatar;
}
