package com.t3h.uniqlo.service.impl;

import com.t3h.uniqlo.dto.UserFormDto;
import com.t3h.uniqlo.dto.UserListItemDto;
import com.t3h.uniqlo.entity.Role;
import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.mapper.UserMapper;
import com.t3h.uniqlo.repository.RoleRepository;
import com.t3h.uniqlo.repository.UserRepository;
import com.t3h.uniqlo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserListItemDto> getUsers(String keyword, String role, int page, int size) {
        return userRepository.findAll(keyword, role, page, size)
                .stream()
                .map(UserMapper::toListItemDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long countUsers(String keyword, String role) {
        return userRepository.countAll(keyword, role);
    }

    @Override
    @Transactional(readOnly = true)
    public UserFormDto getUserForm(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
        return UserMapper.toFormDto(user);
    }

    @Override
    public void createUser(UserFormDto dto) {
        User user = new User();
        UserMapper.copyFormToEntity(dto, user);

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already exists");
        });
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        user.setPasswordHash(dto.getPassword());
        user.setDeleted((byte) 0);

        applyRoleFromDto(user, dto.getRole());

        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer id, UserFormDto dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));

        UserMapper.copyFormToEntity(dto, existing);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPasswordHash(dto.getPassword());
        }

        applyRoleFromDto(existing, dto.getRole());

        userRepository.save(existing);
    }

    private void applyRoleFromDto(User user, String roleName) {
        String normalized = (roleName == null || roleName.isBlank()) ? "USER" : roleName.trim().toUpperCase();

        Role role = roleRepository.findByName(normalized)
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName(normalized);
                    r.setDeleted((byte) 0);
                    return roleRepository.save(r);
                });

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
    }

    @Override
    public void deleteUser(Integer id) {
        int updated = userRepository.softDeleteById(id);
        if (updated == 0) {
            throw new IllegalArgumentException("User not found: " + id);
        }
    }
}
