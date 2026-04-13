package com.t3h.uniqlo.repository.impl;

import com.t3h.uniqlo.entity.Category;
import com.t3h.uniqlo.mapper.CategoryRowMapper;
import com.t3h.uniqlo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM categories WHERE is_deleted = false";
        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }

    @Override
    public Category findById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        List<Category> list = jdbcTemplate.query(sql, new CategoryRowMapper(), id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Category save(Category category) {
        String sql = "INSERT INTO categories (name, parent_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            if (category.getParentId() != null) {
                ps.setInt(2, category.getParentId());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            category.setId(keyHolder.getKey().intValue());
        }
        return category;
    }

    @Override
    public int update(Category category) {
        String sql = "UPDATE categories SET name = ?, parent_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, category.getName(), category.getParentId(), category.getId());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "UPDATE categories SET is_deleted = true WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
