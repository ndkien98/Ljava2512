package com.t3h.userservice.repository;

import com.t3h.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("""
        SELECT u FROM User u
        WHERE u.deleted = 0
          AND (:keyword IS NULL OR u.fullName LIKE :keyword OR u.email LIKE :keyword)
        """)
    Page<User> searchUsers(@Param("keyword") String keyword, Pageable pageable);
}
