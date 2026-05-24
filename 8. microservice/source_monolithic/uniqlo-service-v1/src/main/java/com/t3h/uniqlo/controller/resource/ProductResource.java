package com.t3h.uniqlo.controller.resource;

import com.t3h.uniqlo.dto.ProductRequestDto;
import com.t3h.uniqlo.dto.ProductResponseDto;
import com.t3h.uniqlo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
// tự động inject các servce qua contructor cho các thuộc tính final, vs dụ là productService, thay vì phải dùng @autowired
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    /**
     * Lay danh sach san pham theo phan trang va bo loc.
     *
     * API: GET /api/products
     * @GetMapping: chỉ đjnh method GET
     * ResponseEntity: class chứa cả body và status code của response trả về cho client, giúp dễ dàng kiểm soát response.
     * bao gôm
     *      1. status code
     *      2. body (dữ liệu trả về)
     *      3. message
     */
    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "createdBy", required = false) String createdBy,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "8") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return ResponseEntity.ok(productService.getAllProducts2(keyword, categoryId, createdBy, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(productService.createProduct(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Integer id,
            @RequestBody ProductRequestDto requestDto
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
