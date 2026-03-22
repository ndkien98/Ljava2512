package com.t3h.uniqlo.service;


import com.t3h.uniqlo.dao.ProductionsDao;
import com.t3h.uniqlo.model.dto.ProductionDTO;
import com.t3h.uniqlo.model.response.ProductionResponse;

import java.util.List;

public class ProductionService {

    public ProductionResponse findByCondition(int pageSize, int pageIndex, String keySearch, Integer colorId, Integer categoryId){
        ProductionsDao productionsDao = new ProductionsDao();
        ProductionResponse response = new ProductionResponse();

        Integer totalElements = productionsDao.countProductions(keySearch,colorId,categoryId);
        int totalPages = totalElements/pageSize;
        if (totalElements % pageSize != 0){
            totalPages++;
        }
        response.setCurrentPage(pageIndex);
        response.setPageSize(pageSize);
        List<ProductionDTO> productionDTOS = productionsDao.findByCondition(pageSize,(pageIndex - 1) * pageSize,keySearch,colorId,categoryId);
        response.setData(productionDTOS);
        response.setTotalPages(totalPages);
        return response;
    }
}
