package com.t3h.uniqlo.service;

import com.t3h.uniqlo.dto.request.CreateUserRequest;
import com.t3h.uniqlo.dto.request.UpdateUserRequest;
import com.t3h.uniqlo.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Integer id);
    UserResponse createUser(CreateUserRequest request);
    UserResponse updateUser(Integer id, UpdateUserRequest request);
    void deleteUser(Integer id);
}
