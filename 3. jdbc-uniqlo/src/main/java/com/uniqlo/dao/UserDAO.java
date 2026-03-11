package com.uniqlo.dao;

import com.uniqlo.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends BaseDAO<User> {

    private final RowMapper<User> userMapper = rs -> User.builder()
            .id(rs.getInt("id"))
            .fullName(rs.getString("full_name"))
            .email(rs.getString("email"))
            .passwordHash(rs.getString("password_hash"))
            .birthday(rs.getDate("birthday") != null ? rs.getDate("birthday").toLocalDate() : null)
            .gender(rs.getString("gender"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
            .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
            .build();

    public List<User> findAll() {
        return query("SELECT * FROM users", userMapper);
    }

    public User findById(int id) {
        List<User> users = query("SELECT * FROM users WHERE id = ?", userMapper, id);
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByEmail(String email) {
        List<User> users = query("SELECT * FROM users WHERE email = ?", userMapper, email);
        return users.isEmpty() ? null : users.get(0);
    }

    public int insert(User user) {
        return update("INSERT INTO users (full_name, email, password_hash, birthday, gender) VALUES (?, ?, ?, ?, ?)",
                user.getFullName(), user.getEmail(), user.getPasswordHash(), user.getBirthday(), user.getGender());
    }

    public int update(User user) {
        return update("UPDATE users SET full_name = ?, email = ?, birthday = ?, gender = ? WHERE id = ?",
                user.getFullName(), user.getEmail(), user.getBirthday(), user.getGender(), user.getId());
    }

    public int delete(int id) {
        return update("DELETE FROM users WHERE id = ?", id);
    }
}
