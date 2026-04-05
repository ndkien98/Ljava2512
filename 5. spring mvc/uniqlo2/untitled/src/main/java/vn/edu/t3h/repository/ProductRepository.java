package vn.edu.t3h.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import vn.edu.t3h.mapper.ProductRowMapper;
import vn.edu.t3h.model.Product;

import java.util.List;

public class ProductRepository {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        String sql = "SELECT p.*, c.name as category_name " +
                "FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "ORDER BY p.id DESC";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public Product findById(Integer id) {
        String sql = "SELECT p.*, c.name as category_name " +
                "FROM products p " +
                "LEFT JOIN categories c ON p.category_id = c.id " +
                "WHERE p.id = ?";
        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper(), id);
        return products.isEmpty() ? null : products.get(0);
    }
}
