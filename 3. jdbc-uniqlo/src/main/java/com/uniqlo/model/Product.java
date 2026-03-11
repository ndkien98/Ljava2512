package com.uniqlo.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String description;
    private String materialInfo;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;

    // Additional info for display
    private String categoryName;
}
