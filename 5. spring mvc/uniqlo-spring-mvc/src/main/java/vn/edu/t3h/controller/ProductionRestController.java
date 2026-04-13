package vn.edu.t3h.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.t3h.model.Product;
import vn.edu.t3h.service.ProductService;

import java.util.List;

@RestController
// đánh dấu đây la 1 rest controller chuyên tạo ra các api trả vê dữ liệu dạng json
@RequestMapping("/api/productions")
public class ProductionRestController {

    @Autowired
    private ProductService productService;


    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @GetMapping(produces="application/json")
    public ResponseEntity<List<Product>> getAllProductions() {
        List<Product> products = productService.getAllProducts();
        response.addHeader("Content-Type", "application/json");
        return ResponseEntity.ok(products);
    }

}
