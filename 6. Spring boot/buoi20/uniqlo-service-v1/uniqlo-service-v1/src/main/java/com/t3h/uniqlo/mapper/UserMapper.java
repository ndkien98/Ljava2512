package com.t3h.uniqlo.mapper;

import com.t3h.uniqlo.dto.UserFormDto;
import com.t3h.uniqlo.dto.UserListItemDto;
import com.t3h.uniqlo.entity.Role;
import com.t3h.uniqlo.entity.User;

import java.util.Comparator;

public final class UserMapper {

    private UserMapper() {}

    public static UserListItemDto toListItemDto(User user) {
        if (user == null) return null;
        return UserListItemDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .avatar(user.getAvatar())
                .role(resolveRoleForUi(user))
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static UserFormDto toFormDto(User user) {
        if (user == null) return null;
        return UserFormDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .avatar(user.getAvatar())
                .role(resolveRoleForUi(user))
                .build();
    }

    /**
     * Role cho UI lấy từ quan hệ ManyToMany roles.
     * Ưu tiên ADMIN nếu tồn tại, nếu không thì USER.
     */
    public static String resolveRoleForUi(User user) {
        if (user == null) return "USER";
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            return "USER";
        }
        return user.getRoles().stream()
                .map(Role::getName)
                .filter(n -> n != null && !n.isBlank())
                .map(n -> n.trim().toUpperCase())
                .max(Comparator.comparingInt(r -> "ADMIN".equalsIgnoreCase(r) ? 2 : 1))
                .orElse("USER");
    }

    /**
     * Map dữ liệu từ form DTO sang entity (không set passwordHash ở đây).
     * Role sẽ được gán vào user_roles ở tầng service.
     */
    public static void copyFormToEntity(UserFormDto dto, User entity) {
        if (dto == null || entity == null) return;
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setGender(dto.getGender());
        entity.setBirthday(dto.getBirthday());
        entity.setAvatar(dto.getAvatar());
    }
}
