import api from './axiosConfig';

/**
 * masterDataApi – gọi API danh mục, màu sắc, kích cỡ
 * → Qua Gateway → master-data-service
 */

export const getCategories = () => api.get('/api/categories');
export const getColors = () => api.get('/api/colors');
export const getSizes = () => api.get('/api/sizes');
