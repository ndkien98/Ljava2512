package com.t3h.uniqlo.service;


import com.t3h.uniqlo.dao.ProductionsDao;
import com.t3h.uniqlo.model.ProductionDTO;

import java.util.List;

public class ProductionService {

    public List<ProductionDTO> findByCondition(){
        ProductionsDao productionsDao = new ProductionsDao();
        return productionsDao.findByCondition();
    }
}
