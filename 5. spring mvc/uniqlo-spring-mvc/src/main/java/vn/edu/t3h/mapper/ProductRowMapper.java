package vn.edu.t3h.mapper;

import org.springframework.jdbc.core.RowMapper;
import vn.edu.t3h.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setMaterialInfo(rs.getString("material_info"));
        product.setAvatar(rs.getString("avatar"));
        product.setCreatedAt(rs.getTimestamp("created_at"));
        product.setCreatedBy(rs.getObject("created_by") != null ? rs.getInt("created_by") : null);
        product.setUpdatedAt(rs.getTimestamp("updated_at"));
        product.setUpdatedBy(rs.getObject("updated_by") != null ? rs.getInt("updated_by") : null);

        // Try to map categoryName if available in result set
        try {
            product.setCategoryName(rs.getString("category_name"));
        } catch (SQLException e) {
            // Column may not exist in some queries
        }

        return product;
    }
}
