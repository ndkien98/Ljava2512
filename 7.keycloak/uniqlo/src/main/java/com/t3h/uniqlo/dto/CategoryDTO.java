package com.t3h.uniqlo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    // DTO có thể ẩn bớt một số thông tin nhạy cảm/không cần thiết của Entity.
    // Ở đây ta có thể chỉ trả về id, name, parentId thay vì toàn bộ meta fields.
    private Integer id;
    private String name;
    private Integer parentId;
}
