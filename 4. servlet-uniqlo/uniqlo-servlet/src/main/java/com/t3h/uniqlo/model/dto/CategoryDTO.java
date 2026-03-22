package com.t3h.uniqlo.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private int id;
    private String name;
}
