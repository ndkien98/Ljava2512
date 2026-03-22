package com.t3h.uniqlo.dao;

import com.t3h.uniqlo.mapper.RowMapper;
import com.t3h.uniqlo.model.dto.CategoryDTO;

import java.util.List;

public class CategoryDao extends BaseDao<CategoryDTO> {

    public List<CategoryDTO> findAll() {
        String sql = "SELECT id, name FROM categories WHERE parent_id IS NOT NULL ORDER BY name ASC";
        RowMapper<CategoryDTO> mapper = rs -> CategoryDTO.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
        return query(sql, mapper);
    }
}
