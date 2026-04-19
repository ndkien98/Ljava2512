package com.t3h.uniqlo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity ánh xạ bảng `users` trong database uniqlo_education.
 *
 * DDL tương ứng:
 *   id INT AUTO_INCREMENT PK, full_name, email (UNIQUE), password_hash,
 *   birthday DATE, gender ENUM, role VARCHAR, avatar VARCHAR,
 *   created_at TIMESTAMP, updated_at TIMESTAMP
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    /** Lưu giá trị đã hash bởi BCrypt — KHÔNG bao giờ lưu plain text */
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    private LocalDate birthday;

    /**
     * Dùng String (không Enum Java) để linh hoạt với giá trị DB:
     * 'Male', 'Female', 'Decline to state'
     */
    @Column(columnDefinition = "ENUM('Male','Female','Decline to state')")
    private String gender;

    /**
     * Role: 'USER' hoặc 'ADMIN'
     * DB không có cột này nhưng ta thêm vào để phù hợp UI.
     * Nếu DB chưa có cột role, đổi ddl-auto thành update (chỉ một lần).
     */
    @Column(length = 20, columnDefinition = "varchar(20) default 'USER'")
    @Builder.Default
    private String role = "USER";

    /** URL ảnh đại diện */
    @Column(length = 500)
    private String avatar;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
