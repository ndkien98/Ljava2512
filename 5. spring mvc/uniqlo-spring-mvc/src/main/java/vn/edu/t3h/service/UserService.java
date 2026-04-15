package vn.edu.t3h.service;

import vn.edu.t3h.dto.request.UserCreateRequest;
import vn.edu.t3h.dto.request.UserUpdateRequest;
import vn.edu.t3h.dto.response.UserListItemResponse;
import vn.edu.t3h.model.User;

import java.util.List;

public interface UserService {
    List<UserListItemResponse> getUsers(String keyword, String role);

    User getUserById(Integer id);

    void createUser(UserCreateRequest request);

    void updateUser(UserUpdateRequest request);

    void deleteUser(Integer id);
}

