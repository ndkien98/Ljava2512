package com.t3h.uniqlo.repository;

import com.t3h.uniqlo.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Integer id);
    long countByRole(String role);
    List<User> search(String keyword, String role, int offset, int limit);
    long countSearch(String keyword, String role);
}
