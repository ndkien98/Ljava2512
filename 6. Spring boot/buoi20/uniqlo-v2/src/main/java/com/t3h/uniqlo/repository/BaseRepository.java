package com.t3h.uniqlo.repository;

import java.util.List;
import java.util.Optional;

/**
 * Base Repository interface định nghĩa các luồng CRUD chung.
 */
public interface BaseRepository<T, ID> {
    
    /** Lưu mới (Save) hoặc Cập nhật (Update) entity vào database */
    void save(T entity);

    /** Xoá entity */
    void delete(T entity);

    /** Xoá entity theo ID */
    void deleteById(ID id);

    /** Tìm một entity theo ID */
    Optional<T> findById(ID id);

    /** Lấy toàn bộ danh sách entity */
    List<T> findAll();
}
