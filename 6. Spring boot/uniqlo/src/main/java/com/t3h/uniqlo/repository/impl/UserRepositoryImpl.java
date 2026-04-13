package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.mapper.UserRowMapper;
import com.t3h.uniqlo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY id DESC", new UserRowMapper());
    }

    @Override
    public Optional<User> findById(Integer id) {
        List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);
        return list.stream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE email = ?", new UserRowMapper(), email);
        return list.stream().findFirst();
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (full_name, email, password_hash, birthday, gender, role) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            if (user.getBirthday() != null) {
                ps.setDate(4, java.sql.Date.valueOf(user.getBirthday()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            ps.setString(5, user.getGender());
            ps.setString(6, user.getRole() != null ? user.getRole() : "USER");
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            user.setId(keyHolder.getKey().intValue());
        }
        return user;
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE users SET full_name = ?, birthday = ?, gender = ?, role = ?, avatar = ?, password_hash = ? WHERE id = ?";
        return jdbcTemplate.update(sql, 
                user.getFullName(), 
                user.getBirthday() != null ? java.sql.Date.valueOf(user.getBirthday()) : null,
                user.getGender(), 
                user.getRole(), 
                user.getAvatar(),
                user.getPasswordHash(),
                user.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}
