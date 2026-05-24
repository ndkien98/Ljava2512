package com.t3h.productservice.service;

import com.t3h.productservice.client.MasterDataClient;
import com.t3h.productservice.dto.ProductRequestDto;
import com.t3h.productservice.dto.ProductResponseDto;
import com.t3h.productservice.entity.Product;
import com.t3h.productservice.entity.ProductImage;
import com.t3h.productservice.entity.ProductSku;
import com.t3h.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MasterDataClient masterDataClient;

    public Page<ProductResponseDto> getProducts(String keyword, Integer categoryId, Pageable pageable) {
        String searchKeyword = (keyword != null && !keyword.isBlank()) ? "%" + keyword + "%" : null;

        // Lấy color/size maps một lần để enrich data
        Map<Integer, String> colorMap = masterDataClient.getColorMap();
        Map<Integer, String> sizeMap = masterDataClient.getSizeMap();

        return productRepository.searchProducts(searchKeyword, categoryId, pageable)
            .map(p -> toResponseDto(p, colorMap, sizeMap));
    }

    public ProductResponseDto getProductById(Integer id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm: " + id));

        Map<Integer, String> colorMap = masterDataClient.getColorMap();
        Map<Integer, String> sizeMap = masterDataClient.getSizeMap();

        return toResponseDto(product, colorMap, sizeMap);
    }

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto request) {
        Product product = Product.builder()
            .name(request.getName())
            .description(request.getDescription())
            .materialInfo(request.getMaterialInfo())
            .avatar(request.getAvatar())
            .categoryId(request.getCategoryId())
            .build();
        product.setDeleted((byte) 0);
        product.setCreatedBy("ADMIN");
        product.setUpdatedBy("ADMIN");

        Product saved = productRepository.save(product);

        // Tạo SKUs
        if (request.getSkus() != null) {
            List<ProductSku> skus = request.getSkus().stream().map(skuReq -> {
                ProductSku sku = ProductSku.builder()
                    .product(saved)
                    .colorId(skuReq.getColorId())
                    .sizeId(skuReq.getSizeId())
                    .skuCode(skuReq.getSkuCode())
                    .originalPrice(skuReq.getOriginalPrice())
                    .salePrice(skuReq.getSalePrice())
                    .stockQuantity(skuReq.getStockQuantity())
                    .build();
                sku.setDeleted((byte) 0);
                return sku;
            }).collect(Collectors.toList());
            saved.setSkus(skus);
        }

        // Tạo Images
        if (request.getImages() != null) {
            List<ProductImage> images = request.getImages().stream().map(imgReq -> {
                ProductImage img = ProductImage.builder()
                    .product(saved)
                    .colorId(imgReq.getColorId())
                    .imageUrl(imgReq.getImageUrl())
                    .isMain(imgReq.getIsMain())
                    .sortOrder(imgReq.getSortOrder())
                    .build();
                img.setDeleted((byte) 0);
                return img;
            }).collect(Collectors.toList());
            saved.setImages(images);
        }

        productRepository.save(saved);

        Map<Integer, String> colorMap = masterDataClient.getColorMap();
        Map<Integer, String> sizeMap = masterDataClient.getSizeMap();
        return toResponseDto(saved, colorMap, sizeMap);
    }

    @Transactional
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm: " + id));
        product.setDeleted((byte) 1);   // Soft delete
        productRepository.save(product);
    }

    // Chuyển Product entity → DTO, enrich color/size từ master-data
    private ProductResponseDto toResponseDto(Product p,
                                              Map<Integer, String> colorMap,
                                              Map<Integer, String> sizeMap) {
        List<ProductResponseDto.SkuDto> skuDtos = new ArrayList<>();
        if (p.getSkus() != null) {
            skuDtos = p.getSkus().stream()
                .filter(s -> s.getDeleted() == 0)
                .map(s -> ProductResponseDto.SkuDto.builder()
                    .id(s.getId())
                    .colorId(s.getColorId())
                    .colorCode(colorMap.getOrDefault(s.getColorId(), "N/A"))
                    .sizeId(s.getSizeId())
                    .sizeCode(sizeMap.getOrDefault(s.getSizeId(), "N/A"))
                    .skuCode(s.getSkuCode())
                    .originalPrice(s.getOriginalPrice())
                    .salePrice(s.getSalePrice())
                    .stockQuantity(s.getStockQuantity())
                    .build())
                .collect(Collectors.toList());
        }

        List<ProductResponseDto.ImageDto> imageDtos = new ArrayList<>();
        if (p.getImages() != null) {
            imageDtos = p.getImages().stream()
                .filter(i -> i.getDeleted() == 0)
                .map(i -> ProductResponseDto.ImageDto.builder()
                    .id(i.getId())
                    .colorId(i.getColorId())
                    .imageUrl(i.getImageUrl())
                    .isMain(i.getIsMain())
                    .sortOrder(i.getSortOrder())
                    .build())
                .collect(Collectors.toList());
        }

        return ProductResponseDto.builder()
            .id(p.getId())
            .name(p.getName())
            .description(p.getDescription())
            .materialInfo(p.getMaterialInfo())
            .avatar(p.getAvatar())
            .categoryId(p.getCategoryId())
            .skus(skuDtos)
            .images(imageDtos)
            .build();
    }
}
