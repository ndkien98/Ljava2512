import api from './axiosConfig';

/**
 * userApi – gọi API quản lý users
 * → Qua Gateway → user-service
 */

export const getUsers = (params = {}) => api.get('/api/users', { params });
export const getUserById = (id) => api.get(`/api/users/${id}`);
