package com.t3h.uniqlo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryListItemDto {
    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private LocalDateTime createdAt;
    private String createdBy;
}
