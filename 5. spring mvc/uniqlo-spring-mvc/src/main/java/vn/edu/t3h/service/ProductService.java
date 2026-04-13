package vn.edu.t3h.service;

import vn.edu.t3h.model.Product;
import vn.edu.t3h.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public void demoReflection() {
        try {
            Class<?> clazz = Class.forName("vn.edu.t3h.model.Product");
            System.out.println("Class name: " + clazz.getName());
            System.out.println("Fields:");
            for (var field : clazz.getDeclaredFields()) {
                System.out.println("- " + field.getName() + " (" + field.getType().getSimpleName() + ")");
            }
            System.out.println("Methods:");
            for (var method : clazz.getDeclaredMethods()) {
                System.out.println("- " + method.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return;
    }

    public static void main(String[] args) {
        ProductService service = new ProductService();
        service.demoReflection();
    }
}
