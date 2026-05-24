package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll(String keyword, String role, int page, int size);

    long countAll(String keyword, String role);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    User save(User user);

    int softDeleteById(Integer id);
}
