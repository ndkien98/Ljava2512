package vn.edu.t3h.repository;

import vn.edu.t3h.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll(String keyword, String role);

    User findById(Integer id);

    User findByEmail(String email);

    int insert(User user);

    int update(User user);

    int deleteById(Integer id);
}
