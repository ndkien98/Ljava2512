package com.t3h.uniqlo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * Entity ánh xạ bảng `users`.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    private LocalDate birthday;

    @Column(columnDefinition = "ENUM('Male','Female','Decline to state')")
    private String gender;

    @Column(name = "remember_token", length = 255)
    private String rememberToken;

    /**
     1 user co nhieu quyen
     1 quyen su dung cho nhieu user
     => n-n
     có 1 bảng trung gian ngữa user và role
     ManyToMany: chỉ định mối quan hệ n-n
     fetch = FetchType.LAZY: chỉ định cơ chế query dữ liệu trong entity là lazy load hay EAGER. lấy tất cả
     cascade = {CascadeType.PERSIST, CascadeType.MERGE}: chỉ định cơ chế tác động khi xóa hoặc sửa entity sẽ liên quan đến các entity co
     JoinTable: chỉ định tạo ra table mới liên kết giữa 2 entity tên là: user_roles
     joinColumns, inverseJoinColumns: chỉ dịnh các khóa ngoại tham chiếu đến entity
     */
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Column(length = 500)
    private String avatar;
}
