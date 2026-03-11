package com.uniqlo.dao;

import com.uniqlo.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAO extends BaseDAO<Product> {

    private final RowMapper<Product> productMapper = rs -> Product.builder()
            .id(rs.getInt("id"))
            .categoryId(rs.getInt("category_id"))
            .name(rs.getString("name"))
            .description(rs.getString("description"))
            .materialInfo(rs.getString("material_info"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
            .createdBy(rs.getObject("created_by", Integer.class))
            .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
            .build();

    public List<Product> findAll() {
        return query("SELECT * FROM products", productMapper);
    }

    public Product findById(int id) {
        List<Product> products = query("SELECT * FROM products WHERE id = ?", productMapper, id);
        return products.isEmpty() ? null : products.get(0);
    }

    public int insert(Product product) {
        return update(
                "INSERT INTO products (id, category_id, name, description, material_info, created_by) VALUES (?, ?, ?, ?, ?, ?)",
                product.getId(), product.getCategoryId(), product.getName(), product.getDescription(),
                product.getMaterialInfo(), product.getCreatedBy());
    }

    public int update(Product product) {
        return update(
                "UPDATE products SET category_id = ?, name = ?, description = ?, material_info = ?, updated_by = ? WHERE id = ?",
                product.getCategoryId(), product.getName(), product.getDescription(), product.getMaterialInfo(),
                product.getUpdatedBy(), product.getId());
    }

    public int delete(int id) {
        return update("DELETE FROM products WHERE id = ?", id);
    }

    public List<Product> search(com.uniqlo.util.ProductSearchCriteria criteria) {
        StringBuilder sql = new StringBuilder(
                "SELECT DISTINCT p.*, c.name as category_name " +
                        "FROM products p " +
                        "LEFT JOIN categories c ON p.category_id = c.id " +
                        "LEFT JOIN product_skus ps ON p.id = ps.product_id " +
                        "WHERE 1=1");
        java.util.List<Object> params = new java.util.ArrayList<>();

        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            sql.append(" AND p.name LIKE ?");
            params.add("%" + criteria.getName() + "%");
        }
        if (criteria.getCategoryId() != null) {
            sql.append(" AND p.category_id = ?");
            params.add(criteria.getCategoryId());
        }
        if (criteria.getMinPrice() != null) {
            sql.append(" AND ps.original_price >= ?");
            params.add(criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null) {
            sql.append(" AND ps.original_price <= ?");
            params.add(criteria.getMaxPrice());
        }
        if (criteria.getSkuCode() != null && !criteria.getSkuCode().isEmpty()) {
            sql.append(" AND ps.sku_code = ?");
            params.add(criteria.getSkuCode());
        }

        sql.append(" LIMIT ? OFFSET ?");
        params.add(criteria.getPageSize());
        params.add(criteria.getOffset());

        return query(sql.toString(), rs -> {
            Product p = productMapper.map(rs);
            p.setCategoryName(rs.getString("category_name"));
            return p;
        }, params.toArray());
    }
}
