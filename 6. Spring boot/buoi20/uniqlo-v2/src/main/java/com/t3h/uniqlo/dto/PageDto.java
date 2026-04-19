package com.t3h.uniqlo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Custom DTO wrapper cho kết quả phân trang.
 * Dùng thay thế cho Spring Data Page<T> để độc lập với thư viện data-jpa interface.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;

    public int getTotalPages() {
        return size == 0 ? 1 : (int) Math.ceil((double) totalElements / size);
    }
}
