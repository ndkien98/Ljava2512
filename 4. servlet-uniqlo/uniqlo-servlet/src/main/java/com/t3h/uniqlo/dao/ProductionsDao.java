package com.t3h.uniqlo.dao;

import com.t3h.uniqlo.mapper.RowMapper;
import com.t3h.uniqlo.model.dto.ProductionDTO;

import java.util.List;

public class ProductionsDao extends BaseDao<ProductionDTO>{

    public int countProductions(String keySearch,Integer colorId,Integer categoryId){
        String sql = "select count(*) total\n" +
                "from products p\n" +
                "inner join categories on p.category_id = categories.id\n" +
                "where (? = '' or lower(p.name) like ?)\n" +
                "and (? = -1 or p.category_id = ?)\n" +
                "and (? = -1 or exists(select * from product_skus where product_skus.product_id=p.id and product_skus.color_id=?))";
        return count(sql,keySearch,keySearch,categoryId,categoryId,colorId,colorId);
    }

    public List<ProductionDTO> findByCondition(int pageSize,int offset,String keySearch,Integer colorId,Integer categoryId){
        String sql = "select p.id,p.name name,\n" +
                "       p.description description,\n" +
                "       (select\n" +
                "            min(product_skus.sale_price)\n" +
                "        from product_skus where product_skus.product_id=p.id) sale_price,\n" +
                "        p.avatar image_url,\n" +
                "        categories.name category_name\n" +
                "from products p\n" +
                "inner join categories on p.category_id = categories.id\n" +
                "where (? = '' or lower(p.name) like ?)\n" +
                "and (? = -1 or p.category_id = ?)\n" +
                "and (? = -1 or exists(select * from product_skus where product_skus.product_id=p.id and product_skus.color_id=?))\n" +
                "order by p.id asc\n" +
                "limit ? offset ?";

        RowMapper productionMapper = rs -> ProductionDTO.builder().id(rs.getString("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .salePrice(rs.getBigDecimal("sale_price"))
                .imageUrl(rs.getString("image_url"))
                .categoryName(rs.getString("category_name"))
                .build();
        return query(sql, productionMapper,keySearch,keySearch,categoryId,categoryId,colorId,colorId,pageSize,offset);
    }


}
