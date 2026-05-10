package com.t3h.uniqlo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryFormDto {
    private String name;
    private Integer parentId;
}
