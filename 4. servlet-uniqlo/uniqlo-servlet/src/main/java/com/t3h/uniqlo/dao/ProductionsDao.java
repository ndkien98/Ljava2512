package com.t3h.uniqlo.dao;

import com.t3h.uniqlo.mapper.RowMapper;
import com.t3h.uniqlo.model.dto.ProductionDTO;

import java.util.List;

public class ProductionsDao extends BaseDao<ProductionDTO> {

    private final RowMapper<ProductionDTO> productionMapper = rs -> ProductionDTO.builder()
            .id(rs.getString("id"))
            .name(rs.getString("name"))
            .description(rs.getString("description"))
            .materialInfo(rs.getString("material_info"))
            .salePrice(rs.getBigDecimal("sale_price"))
            .imageUrl(rs.getString("image_url"))
            .categoryName(rs.getString("category_name"))
            .categoryId(rs.getInt("category_id"))
            .build();

    public int countProductions(String keySearch, Integer colorId, Integer categoryId) {
        String sql = "SELECT COUNT(*) total\n" +
                "FROM products p\n" +
                "INNER JOIN categories ON p.category_id = categories.id\n" +
                "WHERE (? = '' OR LOWER(p.name) LIKE CONCAT('%', LOWER(?), '%'))\n" +
                "  AND (? = -1 OR p.category_id = ?)\n" +
                "  AND (? = -1 OR EXISTS(SELECT 1 FROM product_skus WHERE product_skus.product_id = p.id AND product_skus.color_id = ?))";
        return count(sql, keySearch, keySearch, categoryId, categoryId, colorId, colorId);
    }

    public List<ProductionDTO> findByCondition(int pageSize, int offset, String keySearch, Integer colorId, Integer categoryId) {
        String sql = "SELECT p.id, p.name, p.description, p.material_info,\n" +
                "       (SELECT MIN(ps.sale_price) FROM product_skus ps WHERE ps.product_id = p.id) sale_price,\n" +
                "       p.avatar image_url,\n" +
                "       categories.name category_name,\n" +
                "       p.category_id\n" +
                "FROM products p\n" +
                "INNER JOIN categories ON p.category_id = categories.id\n" +
                "WHERE (? = '' OR LOWER(p.name) LIKE CONCAT('%', LOWER(?), '%'))\n" +
                "  AND (? = -1 OR p.category_id = ?)\n" +
                "  AND (? = -1 OR EXISTS(SELECT 1 FROM product_skus ps WHERE ps.product_id = p.id AND ps.color_id = ?))\n" +
                "ORDER BY p.id ASC\n" +
                "LIMIT ? OFFSET ?";
        return query(sql, productionMapper, keySearch, keySearch, categoryId, categoryId, colorId, colorId, pageSize, offset);
    }

    public ProductionDTO findById(int id) {
        String sql = "SELECT p.id, p.name, p.description, p.material_info,\n" +
                "       (SELECT MIN(ps.sale_price) FROM product_skus ps WHERE ps.product_id = p.id) sale_price,\n" +
                "       p.avatar image_url,\n" +
                "       categories.name category_name,\n" +
                "       p.category_id\n" +
                "FROM products p\n" +
                "INNER JOIN categories ON p.category_id = categories.id\n" +
                "WHERE p.id = ?";
        List<ProductionDTO> list = query(sql, productionMapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Insert new product. Returns generated id.
     * Note: products.id is NOT auto_increment in the schema, so we generate a max+1 id.
     */
    public int insert(String name, Integer categoryId, String description, String materialInfo, String avatar) {
        // get next id
        String getMaxId = "SELECT COALESCE(MAX(id), 0) + 1 AS total FROM products";
        int nextId = count(getMaxId);

        String sql = "INSERT INTO products (id, category_id, name, description, material_info, avatar) VALUES (?, ?, ?, ?, ?, ?)";
        update(sql, nextId, categoryId, name, description, materialInfo, avatar);
        return nextId;
    }

    public void updateProduct(int id, String name, Integer categoryId, String description, String materialInfo, String avatar) {
        String sql = "UPDATE products SET name = ?, category_id = ?, description = ?, material_info = ?, avatar = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        update(sql, name, categoryId, description, materialInfo, avatar, id);
    }

    public void deleteProduct(int id) {
        delete("DELETE FROM products WHERE id = ?", id);
    }
}
