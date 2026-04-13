package com.t3h.uniqlo.service.impl;

import com.t3h.uniqlo.constant.ErrorCodes;
import com.t3h.uniqlo.dto.request.CreateUserRequest;
import com.t3h.uniqlo.dto.request.UpdateUserRequest;
import com.t3h.uniqlo.dto.response.UserResponse;
import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.exception.BusinessException;
import com.t3h.uniqlo.exception.ResourceNotFoundException;
import com.t3h.uniqlo.repository.UserRepository;
import com.t3h.uniqlo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USER_NOT_FOUND, "User not found with id: " + id));
        return mapToResponse(user);
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        // Validate if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException(ErrorCodes.VALIDATION_FAILED, "Email already exists", HttpStatus.BAD_REQUEST) {};
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .passwordHash(request.getPassword()) // In real app, must hash password here (e.g. BCrypt)
                .birthday(request.getBirthday())
                .gender(request.getGender())
                .role(request.getRole() != null ? request.getRole() : "USER")
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);
        log.info("User created successfully: {}", savedUser.getId());
        return mapToResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Integer id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USER_NOT_FOUND, "User not found with id: " + id));

        user.setFullName(request.getFullName());
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender());
        user.setRole(request.getRole());
        
        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            user.setPasswordHash(request.getPassword()); // In real app, must hash
        }
        
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.update(user);
        return mapToResponse(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCodes.USER_NOT_FOUND, "User not found with id: " + id));
        userRepository.deleteById(user.getId());
        log.info("User deleted successfully: {}", id);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .role(user.getRole())
                .avatar(user.getAvatar())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
