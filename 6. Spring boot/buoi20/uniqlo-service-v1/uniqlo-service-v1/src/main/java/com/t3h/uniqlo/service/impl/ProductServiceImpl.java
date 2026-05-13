package com.t3h.uniqlo.service.impl;

import com.t3h.uniqlo.dto.ProductImageDto;
import com.t3h.uniqlo.dto.ProductRequestDto;
import com.t3h.uniqlo.dto.ProductResponseDto;
import com.t3h.uniqlo.dto.SkuDto;
import com.t3h.uniqlo.entity.Category;
import com.t3h.uniqlo.entity.Color;
import com.t3h.uniqlo.entity.Product;
import com.t3h.uniqlo.entity.ProductImage;
import com.t3h.uniqlo.entity.ProductSku;
import com.t3h.uniqlo.entity.Size;
import com.t3h.uniqlo.mapper.ProductMapper;
import com.t3h.uniqlo.repository.ColorRepository;
import com.t3h.uniqlo.repository.ProductRepository;
import com.t3h.uniqlo.repository.SizeRepository;
import com.t3h.uniqlo.service.ProductService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    /**
     * Lay tat ca san pham voi cac tieu chi loc va phan trang.
     * Chi lay cac san pham chua bi xoa (deleted = 0).
     */
    @Override
    public Page<ProductResponseDto> getAllProducts(String keyword, Integer categoryId, String createdBy, Pageable pageable) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Loc san pham chua xoa
            predicates.add(criteriaBuilder.equal(root.get("deleted"), 0));

            // Loc theo tu khoa ten san pham
            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + keyword + "%"));
            }
            // Loc theo danh muc
            if (categoryId != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId));
            }
            // Loc theo nguoi tao
            if (createdBy != null && !createdBy.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("createdBy"), createdBy));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return productRepository.findAll(spec, pageable).map(productMapper::toResponseDto);
    }

    /**
     * Lay chi tiet san pham theo ID.
     */
    @Override
    public ProductResponseDto getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay san pham voi id: " + id));
        return productMapper.toResponseDto(product);
    }

    /**
     * Tao moi san pham cung cac thuc the lien quan (SKU, Image).
     */
    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productMapper.toEntity(requestDto);
        product.setDeleted((byte) 0);
        
        // Thiet lap thong tin nguoi tao mac dinh neu khong co
        if (product.getCreatedBy() == null) {
            product.setCreatedBy("ADMIN");
        }
        product.setUpdatedBy(product.getCreatedBy());
        
        // Luu san pham truoc de co ID
        final Product savedProduct = productRepository.save(product);
        
        // Luu cac thuc the lien quan
        saveRelatedEntities(savedProduct, requestDto);
        
        return productMapper.toResponseDto(savedProduct);
    }

    /**
     * Cap nhat thong tin san pham va danh sach SKU/Image.
     * Xoa danh sach cu va ghi lai danh sach moi de dam bao dong bo.
     */
    @Override
    @Transactional
    public ProductResponseDto updateProduct(Integer id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay san pham voi id: " + id));
        
        // Cap nhat cac truong thong tin co ban
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setMaterialInfo(requestDto.getMaterialInfo());
        product.setAvatar(requestDto.getAvatar());
        
        // Cap nhat thong tin nguoi sua
        product.setUpdatedBy("ADMIN");
        
        if (requestDto.getCategoryId() != null) {
            Category cat = new Category();
            cat.setId(requestDto.getCategoryId());
            product.setCategory(cat);
        }

        // Xoa danh sach SKU va Image cu truoc khi them moi
        if (product.getSkus() != null) product.getSkus().clear();
        else product.setSkus(new ArrayList<>());
        
        if (product.getImages() != null) product.getImages().clear();
        else product.setImages(new ArrayList<>());

        // Day cac thay doi xoa vao database
        productRepository.saveAndFlush(product);
        
        // Them moi danh sach SKU va Image tu request
        saveRelatedEntities(product, requestDto);

        return productMapper.toResponseDto(product);
    }

    /**
     * Helper de anh xa va luu danh sach SKU va Image lien ket voi san pham.
     */
    private void saveRelatedEntities(Product product, ProductRequestDto requestDto) {
        // Khoi tao danh sach neu bi null de tranh NPE
        if (product.getSkus() == null) product.setSkus(new ArrayList<>());
        if (product.getImages() == null) product.setImages(new ArrayList<>());

        // Xu ly danh sach SKU (Bien the)
        if (requestDto.getSkus() != null) {
            for (SkuDto skuDto : requestDto.getSkus()) {
                ProductSku sku = new ProductSku();
                sku.setProduct(product);
                sku.setSkuCode(skuDto.getSkuCode());
                sku.setOriginalPrice(skuDto.getOriginalPrice());
                sku.setSalePrice(skuDto.getSalePrice());
                sku.setStockQuantity(skuDto.getStockQuantity());
                
                // Gan Color va Size tu ID
                Color color = colorRepository.findById(skuDto.getColorId()).orElse(null);
                Size size = sizeRepository.findById(skuDto.getSizeId()).orElse(null);
                sku.setColor(color);
                sku.setSize(size);
                
                product.getSkus().add(sku);
            }
        }

        // Xu ly danh sach Hinh anh chi tiet
        if (requestDto.getImages() != null) {
            for (ProductImageDto imgDto : requestDto.getImages()) {
                ProductImage img = new ProductImage();
                img.setProduct(product);
                img.setImageUrl(imgDto.getImageUrl());
                img.setIsMain(imgDto.getIsMain());
                img.setSortOrder(imgDto.getSortOrder());
                
                // Gan Color cho anh neu co
                Color color = colorRepository.findById(imgDto.getColorId()).orElse(null);
                img.setColor(color);
                
                product.getImages().add(img);
            }
        }
        // Luu lai san pham voi cac thuc the con da duoc set
        productRepository.save(product);
    }

    /**
     * Xoa mem san pham bang cach set deleted = 1.
     */
    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay san pham voi id: " + id));
        product.setDeleted((byte) 1);
        productRepository.save(product);
    }
}
