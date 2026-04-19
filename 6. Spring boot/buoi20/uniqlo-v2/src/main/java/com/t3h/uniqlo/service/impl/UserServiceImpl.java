package com.t3h.uniqlo.service.impl;

import com.t3h.uniqlo.dto.PageDto;
import com.t3h.uniqlo.dto.UserDto;
import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.repository.UserRepository;
import com.t3h.uniqlo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional // <-- Spring sẽ tự động mở/đóng và commit/rollback Hibernate Session
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public PageDto<User> findAll(String keyword, String role, int page, int size) {
        int offset = page * size;
        List<User> content = userRepository.search(keyword, role, offset, size);
        long totalElements = userRepository.countSearch(keyword, role);

        return new PageDto<>(content, page, size, totalElements);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng với ID: " + id));
    }

    @Override
    public User create(UserDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }

        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .passwordHash(passwordEncoder.encode(dto.getPassword()))
                .birthday(dto.getBirthday())
                .gender(dto.getGender())
                .role(dto.getRole())
                .avatar(dto.getAvatar())
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public User update(Integer id, UserDto dto) {
        User user = findById(id);

        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new IllegalArgumentException("Email đã được sử dụng bởi tài khoản khác");
        }

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setBirthday(dto.getBirthday());
        user.setGender(dto.getGender());
        user.setRole(dto.getRole());
        user.setAvatar(dto.getAvatar());

        // Chỉ hash và cập nhật password nếu có nhập mật khẩu mới
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        }

        // Với Hibernate ở trong @Transactional, thay đổi trên đối tượng `user` (persistent state)
        // sẽ tự động được flush xuống DB khi commit transaction. Tuy nhiên, gọi save()/merge()
        // vẫn là thói quen tốt để code rõ ràng.
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Integer id) {
        User user = findById(id);

        if ("ADMIN".equals(user.getRole())) {
            long adminCount = userRepository.countByRole("ADMIN");
            if (adminCount <= 1) {
                throw new IllegalStateException("Không thể xoá quản trị viên duy nhất của hệ thống");
            }
        }

        userRepository.delete(user);
    }

    @Override
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        // KHÔNG trả password_hash ra ngoài DTO
        dto.setBirthday(user.getBirthday());
        dto.setGender(user.getGender());
        dto.setRole(user.getRole());
        dto.setAvatar(user.getAvatar());
        return dto;
    }
}
