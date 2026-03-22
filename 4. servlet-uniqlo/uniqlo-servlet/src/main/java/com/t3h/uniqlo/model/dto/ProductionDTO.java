package com.t3h.uniqlo.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/*
sử dụng lombok @Data sẽ tự động tạo ra các phương thức getter, setter, toString, equals và hashCode cho tất cả các trường trong lớp ProductionDTO. Điều này giúp giảm bớt mã boilerplate và làm cho mã nguồn trở nên sạch sẽ hơn.
 */
@Data
@Builder
@ToString
public class ProductionDTO {

    private String id;
    private String name;
    private String description;
    private BigDecimal salePrice;
    private String imageUrl;
    private String categoryName;

}
