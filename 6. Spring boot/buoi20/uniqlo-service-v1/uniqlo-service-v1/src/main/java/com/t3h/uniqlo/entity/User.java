package com.t3h.uniqlo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
public class User extends BaseEntity{

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
    role có thể có nhiều user, 1 user có thể đảm nhận nhiều role => quan hệ ManyToMany
     => để thể hiện được mối quan hệ n-n giữa User và Role ta cần 1 bảng trung gian (join table) để lưu trữ các cặp user_id - role_id
     */
    @ManyToMany( // thể hiện quan hệ nhiều-nhiều giữa User và Role
            fetch = FetchType.LAZY, // chỉ tải roles khi cần thiết, tránh truy vấn thừa khi lấy user
            cascade = {CascadeType.PERSIST, CascadeType.MERGE} // khi lưu hoặc cập nhật user, tự động lưu hoặc cập nhật role nếu cần thiết
    )
    @JoinTable( // định nghĩa bảng trung gian để lưu quan hệ nhiều-nhiều giữa User và Role
        name = "user_roles", // tên bảng trung gian
        joinColumns = @JoinColumn(name = "user_id"), // chỉ định cột khóa ngoại liên kết đến bảng User trong table trung gian
        inverseJoinColumns = @JoinColumn(name = "role_id") // chỉ định cột khóa ngoại liên kết đến bảng Role trong table trung gian
    )
    private Set<Role> roles;

    /** URL ảnh đại diện */
    @Column(length = 500)
    private String avatar;


}
