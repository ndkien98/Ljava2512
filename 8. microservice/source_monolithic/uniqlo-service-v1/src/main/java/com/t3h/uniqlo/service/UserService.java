package com.t3h.uniqlo.service;

import com.t3h.uniqlo.dto.UserFormDto;
import com.t3h.uniqlo.dto.UserListItemDto;

import java.util.List;

public interface UserService {

    List<UserListItemDto> getUsers(String keyword, String role, int page, int size);

    long countUsers(String keyword, String role);

    UserFormDto getUserForm(Integer id);

    void createUser(UserFormDto dto);

    void updateUser(Integer id, UserFormDto dto);

    void deleteUser(Integer id);
}
