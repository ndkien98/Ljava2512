import axios from 'axios';

/**
 * Axios instance được cấu hình để gọi API Gateway
 *
 * proxy: "http://localhost:8080" trong package.json
 * → tất cả request /api/... sẽ được proxy đến Gateway
 * → Gateway điều phối sang đúng microservice
 */
const api = axios.create({
  baseURL: '/',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// ── Request Interceptor ────────────────────────────────────────────────────
// Tự động đính JWT token vào mọi request
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('access_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// ── Response Interceptor ───────────────────────────────────────────────────
// Tự động xử lý 401 (token hết hạn)
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const refreshToken = localStorage.getItem('refresh_token');
        const response = await axios.post('/api/v1/auth/refresh', {}, {
          headers: { Authorization: `Bearer ${refreshToken}` },
        });

        const { access_token } = response.data;
        localStorage.setItem('access_token', access_token);

        // Retry request ban đầu với token mới
        originalRequest.headers.Authorization = `Bearer ${access_token}`;
        return api(originalRequest);
      } catch (refreshError) {
        // Refresh thất bại → logout
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
        window.location.href = '/login';
        return Promise.reject(refreshError);
      }
    }

    return Promise.reject(error);
  }
);

export default api;
