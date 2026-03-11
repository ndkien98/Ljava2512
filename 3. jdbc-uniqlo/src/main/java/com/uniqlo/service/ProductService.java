package com.uniqlo.service;

import com.uniqlo.dao.ProductDAO;
import com.uniqlo.model.Product;
import com.uniqlo.util.ProductSearchCriteria;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public boolean createProduct(Product product) {
        return productDAO.insert(product) > 0;
    }

    public boolean updateProduct(Product product) {
        return productDAO.update(product) > 0;
    }

    public boolean deleteProduct(int id) {
        return productDAO.delete(id) > 0;
    }

    public List<Product> searchProducts(ProductSearchCriteria criteria) {
        return productDAO.search(criteria);
    }
}
