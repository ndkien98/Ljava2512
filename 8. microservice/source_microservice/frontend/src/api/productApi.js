import api from './axiosConfig';

/**
 * productApi – các hàm gọi API sản phẩm
 * → Qua Gateway → product-service
 */

// Lấy danh sách sản phẩm có phân trang và filter
export const getProducts = (params = {}) =>
  api.get('/api/products', { params });

// Lấy chi tiết sản phẩm
export const getProductById = (id) =>
  api.get(`/api/products/${id}`);

// Tạo sản phẩm mới
export const createProduct = (data) =>
  api.post('/api/products', data);

// Cập nhật sản phẩm
export const updateProduct = (id, data) =>
  api.put(`/api/products/${id}`, data);

// Xóa sản phẩm (soft delete)
export const deleteProduct = (id) =>
  api.delete(`/api/products/${id}`);
