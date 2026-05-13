package com.t3h.uniqlo.mapper;

import com.t3h.uniqlo.dto.ProductImageDto;
import com.t3h.uniqlo.dto.ProductRequestDto;
import com.t3h.uniqlo.dto.ProductResponseDto;
import com.t3h.uniqlo.dto.SkuDto;
import com.t3h.uniqlo.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponseDto  toResponseDto(Product product);

    @Mapping(target = "category", source = "categoryId", qualifiedByName = "idToCategory")
    @Mapping(target = "skus", ignore = true)
    @Mapping(target = "images", ignore = true)
    Product toEntity(ProductRequestDto requestDto);

    @Mapping(target = "colorId", source = "color.id")
    @Mapping(target = "colorCode", source = "color.colorCode")
    @Mapping(target = "sizeId", source = "size.id")
    @Mapping(target = "sizeCode", source = "size.sizeCode")
    SkuDto toSkuDto(ProductSku sku);

    @Mapping(target = "colorId", source = "color.id")
    ProductImageDto toImageDto(ProductImage image);

    @Named("idToCategory")
    default Category idToCategory(Integer categoryId) {
        if (categoryId == null) return null;
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }
}
