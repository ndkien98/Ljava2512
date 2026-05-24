package com.t3h.userservice.service;

import com.t3h.userservice.dto.UserDto;
import com.t3h.userservice.entity.User;
import com.t3h.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<UserDto> getUsers(String keyword, Pageable pageable) {
        String searchKeyword = (keyword != null && !keyword.isBlank())
            ? "%" + keyword + "%" : null;
        return userRepository.searchUsers(searchKeyword, pageable)
            .map(this::toDto);
    }

    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found: " + id));
        return toDto(user);
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
            .id(user.getId())
            .fullName(user.getFullName())
            .email(user.getEmail())
            .gender(user.getGender())
            .avatar(user.getAvatar())
            .roles(user.getRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toList()))
            .build();
    }
}
