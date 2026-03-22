package com.t3h.uniqlo.service;

import com.t3h.uniqlo.dao.CategoryDao;
import com.t3h.uniqlo.dao.ColorDao;
import com.t3h.uniqlo.dao.ProductionsDao;
import com.t3h.uniqlo.model.dto.CategoryDTO;
import com.t3h.uniqlo.model.dto.ColorDTO;
import com.t3h.uniqlo.model.dto.ProductionDTO;
import com.t3h.uniqlo.model.response.ProductionResponse;

import java.util.List;

public class ProductionService {

    private final ProductionsDao productionsDao = new ProductionsDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final ColorDao colorDao = new ColorDao();

    public ProductionResponse findByCondition(int pageSize, int pageIndex, String keySearch, Integer colorId, Integer categoryId) {
        ProductionResponse response = new ProductionResponse();

        Integer totalElements = productionsDao.countProductions(keySearch, colorId, categoryId);
        int totalPages = totalElements / pageSize;
        if (totalElements % pageSize != 0) {
            totalPages++;
        }
        if (totalPages == 0) totalPages = 1;

        List<ProductionDTO> data = productionsDao.findByCondition(pageSize, (pageIndex - 1) * pageSize, keySearch, colorId, categoryId);
        response.setData(data);
        response.setTotalPages(totalPages);
        response.setCurrentPage(pageIndex);
        response.setPageSize(pageSize);
        return response;
    }

    public ProductionDTO findById(int id) {
        return productionsDao.findById(id);
    }

    public void save(String name, Integer categoryId, String description, String materialInfo, String avatar, Integer id) {
        if (id == null || id <= 0) {
            productionsDao.insert(name, categoryId, description, materialInfo, avatar);
        } else {
            productionsDao.updateProduct(id, name, categoryId, description, materialInfo, avatar);
        }
    }

    public void delete(int id) {
        productionsDao.deleteProduct(id);
    }

    public List<CategoryDTO> getCategories() {
        return categoryDao.findAll();
    }

    public List<ColorDTO> getColors() {
        return colorDao.findAll();
    }
}
