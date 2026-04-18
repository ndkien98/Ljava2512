package com.t3h.uniqlo.mapper;

import com.t3h.uniqlo.entity.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .fullName(rs.getString("full_name"))
                .email(rs.getString("email"))
                .passwordHash(rs.getString("password_hash"))
                .birthday(rs.getDate("birthday") != null ? rs.getDate("birthday").toLocalDate() : null)
                .gender(rs.getString("gender"))
                .role(rs.getString("role"))
                .rememberToken(rs.getString("remember_token"))
                .avatar(rs.getString("avatar"))
                .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                .updatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null)
                .build();
    }
}
