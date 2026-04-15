package vn.edu.t3h.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import vn.edu.t3h.model.User;
import vn.edu.t3h.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setFullName(rs.getString("full_name"));
            u.setEmail(rs.getString("email"));
            u.setPasswordHash(rs.getString("password_hash"));

            java.sql.Date bd = rs.getDate("birthday");
            if (bd != null) u.setBirthday(bd.toLocalDate());

            u.setGender(rs.getString("gender"));
            try { u.setRole(rs.getString("role")); } catch (SQLException ignore) {}
            try { u.setRememberToken(rs.getString("remember_token")); } catch (SQLException ignore) {}
            try { u.setAvatar(rs.getString("avatar")); } catch (SQLException ignore) {}

            Timestamp created = rs.getTimestamp("created_at");
            if (created != null) u.setCreatedAt(created.toLocalDateTime());
            Timestamp updated = rs.getTimestamp("updated_at");
            if (updated != null) u.setUpdatedAt(updated.toLocalDateTime());

            return u;
        }
    };

    @Override
    public List<User> findAll(String keyword, String role) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, full_name, email, password_hash, birthday, gender, role, remember_token, avatar, created_at, updated_at ")
           .append("FROM users WHERE 1=1 ");

        List<Object> args = new ArrayList<>();

        if (keyword != null && !keyword.isBlank()) {
            sql.append(" AND (full_name LIKE ? OR email LIKE ?) ");
            String kw = "%" + keyword.trim() + "%";
            args.add(kw);
            args.add(kw);
        }

        if (role != null && !role.isBlank() && !"ALL".equalsIgnoreCase(role)) {
            sql.append(" AND role = ? ");
            args.add(role.trim());
        }

        sql.append(" ORDER BY id DESC");
        return jdbcTemplate.query(sql.toString(), rowMapper, args.toArray());
    }

    @Override
    public User findById(Integer id) {
        String sql = "SELECT id, full_name, email, password_hash, birthday, gender, role, remember_token, avatar, created_at, updated_at FROM users WHERE id = ?";
        List<User> list = jdbcTemplate.query(sql, rowMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT id, full_name, email, password_hash, birthday, gender, role, remember_token, avatar, created_at, updated_at FROM users WHERE email = ?";
        List<User> list = jdbcTemplate.query(sql, rowMapper, email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int insert(User user) {
        String sql = "INSERT INTO users (full_name, email, password_hash, birthday, gender, role, avatar) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                user.getFullName(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getBirthday() == null ? null : java.sql.Date.valueOf(user.getBirthday()),
                user.getGender(),
                user.getRole() == null || user.getRole().isBlank() ? "USER" : user.getRole(),
                user.getAvatar()
        );
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE users SET full_name = ?, email = ?, password_hash = ?, birthday = ?, gender = ?, role = ?, avatar = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                user.getFullName(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getBirthday() == null ? null : java.sql.Date.valueOf(user.getBirthday()),
                user.getGender(),
                user.getRole(),
                user.getAvatar(),
                user.getId()
        );
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

