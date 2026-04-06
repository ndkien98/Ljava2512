package vn.edu.t3h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.t3h.model.Product;
import vn.edu.t3h.service.ProductService;

import java.util.List;


@Controller // sử dụng @Controller để đánh dấu đây là một controller trong Spring MVC, xử lý các request tương tự servlet
@RequestMapping("/products") // Config đường dẫn gốc mà controller sẽ tiếp nhận xử lý
public class ProductController {

    // @Autowired// là 1 annnotation của spring được sử dụng để spring tự động tìm kiếm các bean và inject vào field này.
    private ProductService productService;

    // tiêm sử dụng constructor để tiêm dependency, cách này được khuyến khích hơn vì giúp code dễ test hơn
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping// khai báo phương thức xử lý GET của HTTP, tương tự doGet trong servlet
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product_list";
    }

    @GetMapping("/{id}")
    public String viewProductDetail(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product_detail";
    }
}
