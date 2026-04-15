package vn.edu.t3h.service.impl;

import vn.edu.t3h.dto.request.UserCreateRequest;
import vn.edu.t3h.dto.request.UserUpdateRequest;
import vn.edu.t3h.dto.response.UserListItemResponse;
import vn.edu.t3h.exception.NotFoundException;
import vn.edu.t3h.exception.ValidationException;
import vn.edu.t3h.mapper.UserMapper;
import vn.edu.t3h.model.User;
import vn.edu.t3h.repository.UserRepository;
import vn.edu.t3h.service.UserService;
import vn.edu.t3h.util.PasswordUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserListItemResponse> getUsers(String keyword, String role) {
        return userRepository.findAll(keyword, role)
                .stream()
                .map(UserMapper::toListItem)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Integer id) {
        User u = userRepository.findById(id);
        if (u == null) throw new NotFoundException("User not found: id=" + id);
        return u;
    }

    @Override
    public void createUser(UserCreateRequest request) {
        Map<String, String> errors = validateCreate(request);
        if (!errors.isEmpty()) {
            throw new ValidationException("Invalid create user", errors);
        }

        if (userRepository.findByEmail(request.getEmail().trim()) != null) {
            errors.put("email", "Email already exists");
            throw new ValidationException("Invalid create user", errors);
        }

        User user = UserMapper.fromCreateRequest(request);
        user.setPasswordHash(PasswordUtil.md5(request.getPassword()));
        if (user.getRole() == null || user.getRole().isBlank()) user.setRole("USER");

        userRepository.insert(user);
    }

    @Override
    public void updateUser(UserUpdateRequest request) {
        Map<String, String> errors = validateUpdate(request);
        if (!errors.isEmpty()) {
            throw new ValidationException("Invalid update user", errors);
        }

        User existing = userRepository.findById(request.getId());
        if (existing == null) {
            throw new NotFoundException("User not found: id=" + request.getId());
        }

        User byEmail = userRepository.findByEmail(request.getEmail().trim());
        if (byEmail != null && !byEmail.getId().equals(existing.getId())) {
            errors.put("email", "Email already exists");
            throw new ValidationException("Invalid update user", errors);
        }

        User update = UserMapper.fromUpdateRequest(request);

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            update.setPasswordHash(existing.getPasswordHash());
        } else {
            update.setPasswordHash(PasswordUtil.md5(request.getPassword()));
        }

        if (update.getRole() == null || update.getRole().isBlank()) update.setRole(existing.getRole());

        userRepository.update(update);
    }

    @Override
    public void deleteUser(Integer id) {
        User existing = userRepository.findById(id);
        if (existing == null) throw new NotFoundException("User not found: id=" + id);
        userRepository.deleteById(id);
    }

    private Map<String, String> validateCreate(UserCreateRequest r) {
        Map<String, String> errors = new HashMap<>();
        if (r.getFullName() == null || r.getFullName().isBlank()) errors.put("fullName", "Full name is required");
        if (r.getEmail() == null || r.getEmail().isBlank()) {
            errors.put("email", "Email is required");
        } else if (!EMAIL.matcher(r.getEmail().trim()).matches()) {
            errors.put("email", "Email format is invalid");
        }
        if (r.getPassword() == null || r.getPassword().isBlank()) errors.put("password", "Password is required");
        return errors;
    }

    private Map<String, String> validateUpdate(UserUpdateRequest r) {
        Map<String, String> errors = new HashMap<>();
        if (r.getId() == null) errors.put("id", "Id is required");
        if (r.getFullName() == null || r.getFullName().isBlank()) errors.put("fullName", "Full name is required");
        if (r.getEmail() == null || r.getEmail().isBlank()) {
            errors.put("email", "Email is required");
        } else if (!EMAIL.matcher(r.getEmail().trim()).matches()) {
            errors.put("email", "Email format is invalid");
        }
        return errors;
    }
}

