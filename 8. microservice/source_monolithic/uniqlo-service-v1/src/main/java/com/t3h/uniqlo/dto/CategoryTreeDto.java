package com.t3h.uniqlo.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryTreeDto {
    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
    private LocalDateTime createdAt;
    private String createdBy;
    private int depth; 

    @Builder.Default
    private List<CategoryTreeDto> children = new ArrayList<>();
}
