import api from './axiosConfig';

/**
 * authApi – các hàm gọi API xác thực
 * → Qua Gateway → user-service
 */

// Đăng nhập
export const login = (email, password) =>
  api.post('/api/v1/auth/login', { email, password });

// Đăng xuất (xóa token local)
export const logout = () => {
  localStorage.removeItem('access_token');
  localStorage.removeItem('refresh_token');
};
