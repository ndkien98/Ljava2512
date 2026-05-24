package com.t3h.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "material_info", columnDefinition = "TEXT")
    private String materialInfo;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    // Chỉ lưu categoryId (không join sang master-data-service qua JPA)
    // Lý do: trong microservice, các service không chia sẻ entity với nhau
    @Column(name = "category_id")
    private Integer categoryId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSku> skus;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;
}
