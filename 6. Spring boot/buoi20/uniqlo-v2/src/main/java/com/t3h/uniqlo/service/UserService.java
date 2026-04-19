package com.t3h.uniqlo.service;

import com.t3h.uniqlo.dto.PageDto;
import com.t3h.uniqlo.dto.UserDto;
import com.t3h.uniqlo.entity.User;

/**
 * Interface tầng Service cho User.
 * Tách interface để dễ mock khi test và tuân theo nguyên tắc DIP.
 */
public interface UserService {

    /**
     * Tìm kiếm + phân trang người dùng.
     *
     * @param keyword từ khoá tìm trong fullName hoặc email
     * @param role    lọc theo role ('USER' | 'ADMIN' | null/empty = tất cả)
     * @param page    số trang (0-indexed)
     * @param size    số bản ghi mỗi trang
     * @return PageDto chứa danh sách User
     */
    PageDto<User> findAll(String keyword, String role, int page, int size);

    /**
     * Tìm user theo id.
     *
     * @throws jakarta.persistence.EntityNotFoundException nếu không tìm thấy
     */
    User findById(Integer id);

    /**
     * Tạo user mới. Hash password trước khi lưu.
     *
     * @throws IllegalArgumentException nếu email đã tồn tại
     */
    User create(UserDto dto);

    /**
     * Cập nhật thông tin user.
     * Chỉ hash lại password nếu dto.password không rỗng.
     *
     * @throws IllegalArgumentException nếu email đã tồn tại ở user khác
     */
    User update(Integer id, UserDto dto);

    /**
     * Xoá user theo id.
     *
     * @throws IllegalStateException nếu cố xoá admin duy nhất còn lại
     */
    void delete(Integer id);

    /** Chuyển đổi User entity → UserDto (dùng cho form edit) */
    UserDto toDto(User user);
}
