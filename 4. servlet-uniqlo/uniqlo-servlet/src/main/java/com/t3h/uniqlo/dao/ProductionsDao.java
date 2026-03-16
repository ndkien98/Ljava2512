package com.t3h.uniqlo.dao;

import com.t3h.uniqlo.mapper.RowMapper;
import com.t3h.uniqlo.model.ProductionDTO;

import java.util.List;

public class ProductionsDao extends BaseDao<ProductionDTO>{

    public List<ProductionDTO> findByCondition(){
        String sql = "select distinct p.id as id,\n" +
                "                concat(p.name,' ',psk.sku_code,' ',co.color_code) name,\n" +
                "                p.description,\n" +
                "                psk.sale_price,\n" +
                "                pi.image_url,\n" +
                "                c.name as category_name from product_skus psk\n" +
                "inner join products p on p.id = psk.product_id\n" +
                "inner join product_images pi on pi.product_id = p.id\n" +
                "inner join categories c on c.id = p.category_id\n" +
                "inner join colors co on co.id = psk.color_id";

        RowMapper productionMapper = rs -> ProductionDTO.builder().id(rs.getString("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .salePrice(rs.getBigDecimal("sale_price"))
                .imageUrl(rs.getString("image_url"))
                .categoryName(rs.getString("category_name"))
                .build();
        return query(sql, productionMapper);
    }


}
