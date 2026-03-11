package com.uniqlo.util;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductSearchCriteria {
    private String name;
    private Integer categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String skuCode;

    // Pagination fields
    private int page;
    private int pageSize;

    public int getOffset() {
        return (page - 1) * pageSize;
    }
}
