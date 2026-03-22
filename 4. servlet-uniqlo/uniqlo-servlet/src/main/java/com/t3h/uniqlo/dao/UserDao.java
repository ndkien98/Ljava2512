package com.t3h.uniqlo.dao;

import com.t3h.uniqlo.model.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends BaseDao<UserDTO> {

    private UserDTO mapRow(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setId(rs.getInt("id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setGender(rs.getString("gender"));
        user.setRole(rs.getString("role"));
        user.setRememberToken(rs.getString("remember_token"));
        user.setAvatar(rs.getString("avatar"));
        if (rs.getDate("birthday") != null) {
            user.setBirthday(rs.getDate("birthday").toLocalDate());
        }
        if (rs.getTimestamp("created_at") != null) {
            user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        return user;
    }

    public UserDTO findByEmailAndPassword(String email, String passwordHash) {
        String sql = "SELECT * FROM users WHERE email = ? AND password_hash = ? LIMIT 1";
        List<UserDTO> users = query(sql, rs -> mapRow(rs), email, passwordHash);
        return users.isEmpty() ? null : users.get(0);
    }

    public UserDTO findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ? LIMIT 1";
        List<UserDTO> users = query(sql, rs -> mapRow(rs), email);
        return users.isEmpty() ? null : users.get(0);
    }

    public UserDTO findByRememberToken(String token) {
        String sql = "SELECT * FROM users WHERE remember_token = ? LIMIT 1";
        List<UserDTO> users = query(sql, rs -> mapRow(rs), token);
        return users.isEmpty() ? null : users.get(0);
    }

    public int save(UserDTO user) {
        String sql = "INSERT INTO users (full_name, email, password_hash, gender, role, avatar) VALUES (?, ?, ?, ?, ?, ?)";
        return insert(sql,
                user.getFullName(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getGender(),
                user.getRole() != null ? user.getRole() : "USER",
                user.getAvatar()
        );
    }

    public void updateRememberToken(int userId, String token) {
        String sql = "UPDATE users SET remember_token = ? WHERE id = ?";
        update(sql, token, userId);
    }

    public void clearRememberToken(int userId) {
        String sql = "UPDATE users SET remember_token = NULL WHERE id = ?";
        update(sql, userId);
    }
}
