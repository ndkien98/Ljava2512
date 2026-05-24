import React, { useEffect, useState } from 'react';
import { getProducts, deleteProduct } from '../api/productApi';
import { getCategories } from '../api/masterDataApi';

/**
 * ProductPage – trang quản lý sản phẩm
 *
 * Ví dụ về cách React gọi 2 microservice khác nhau:
 * - getProducts()   → Gateway → PRODUCT-SERVICE (8082)
 * - getCategories() → Gateway → MASTER-DATA-SERVICE (8083)
 *
 * Tất cả đều qua cùng 1 URL: http://localhost:8080
 * Gateway tự phân phối dựa trên path
 */
export default function ProductPage() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [keyword, setKeyword] = useState('');
  const [categoryId, setCategoryId] = useState('');
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  // Load categories từ master-data-service (chỉ cần load 1 lần)
  useEffect(() => {
    getCategories()
      .then(res => setCategories(res.data))
      .catch(console.error);
  }, []);

  // Load products từ product-service khi filter thay đổi
  useEffect(() => {
    loadProducts();
  }, [page, categoryId]);

  const loadProducts = async () => {
    setLoading(true);
    try {
      const params = { page, size: 8 };
      if (keyword) params.keyword = keyword;
      if (categoryId) params.categoryId = categoryId;

      const res = await getProducts(params);
      setProducts(res.data.content);
      setTotalPages(res.data.totalPages);
    } catch (error) {
      console.error('Lỗi khi tải sản phẩm:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = (e) => {
    e.preventDefault();
    setPage(0);
    loadProducts();
  };

  const handleDelete = async (id) => {
    if (!window.confirm('Bạn có chắc muốn xóa sản phẩm này?')) return;
    try {
      await deleteProduct(id);
      loadProducts();
    } catch (error) {
      alert('Xóa thất bại!');
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Quản lý sản phẩm</h2>

      {/* Filter bar */}
      <form onSubmit={handleSearch} style={styles.filterBar}>
        <input
          type="text"
          value={keyword}
          onChange={e => setKeyword(e.target.value)}
          placeholder="Tìm kiếm tên sản phẩm..."
          style={styles.input}
        />
        <select
          value={categoryId}
          onChange={e => { setCategoryId(e.target.value); setPage(0); }}
          style={styles.select}
        >
          <option value="">-- Tất cả danh mục --</option>
          {categories.map(cat => (
            <option key={cat.id} value={cat.id}>{cat.name}</option>
          ))}
        </select>
        <button type="submit" style={styles.searchBtn}>Tìm kiếm</button>
      </form>

      {/* Product Table */}
      {loading ? (
        <p>Đang tải...</p>
      ) : (
        <table style={styles.table}>
          <thead>
            <tr style={styles.tableHeader}>
              <th>ID</th>
              <th>Ảnh</th>
              <th>Tên sản phẩm</th>
              <th>Danh mục</th>
              <th>Số SKU</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            {products.map(product => (
              <tr key={product.id} style={styles.tableRow}>
                <td>{product.id}</td>
                <td>
                  {product.avatar && (
                    <img src={product.avatar} alt={product.name}
                      style={{ width: 50, height: 50, objectFit: 'cover' }} />
                  )}
                </td>
                <td style={styles.productName}>{product.name}</td>
                <td>{product.categoryId}</td>
                <td>{product.skus?.length || 0}</td>
                <td>
                  <button
                    onClick={() => handleDelete(product.id)}
                    style={styles.deleteBtn}
                  >
                    Xóa
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {/* Pagination */}
      <div style={styles.pagination}>
        <button
          onClick={() => setPage(p => Math.max(0, p - 1))}
          disabled={page === 0}
          style={styles.pageBtn}
        >
          ← Trước
        </button>
        <span style={{ margin: '0 16px' }}>
          Trang {page + 1} / {totalPages}
        </span>
        <button
          onClick={() => setPage(p => p + 1)}
          disabled={page >= totalPages - 1}
          style={styles.pageBtn}
        >
          Sau →
        </button>
      </div>
    </div>
  );
}

const styles = {
  container: { padding: '24px' },
  title: { fontSize: '24px', marginBottom: '20px' },
  filterBar: { display: 'flex', gap: '12px', marginBottom: '20px' },
  input: { flex: 1, padding: '8px 12px', border: '1px solid #ddd', borderRadius: '4px' },
  select: { padding: '8px 12px', border: '1px solid #ddd', borderRadius: '4px' },
  searchBtn: { padding: '8px 20px', backgroundColor: '#000', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer' },
  table: { width: '100%', borderCollapse: 'collapse' },
  tableHeader: { backgroundColor: '#f5f5f5' },
  tableRow: { borderBottom: '1px solid #eee' },
  productName: { fontWeight: '500' },
  deleteBtn: { padding: '4px 12px', backgroundColor: '#ff4444', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer' },
  pagination: { display: 'flex', alignItems: 'center', justifyContent: 'center', marginTop: '24px' },
  pageBtn: { padding: '8px 16px', border: '1px solid #ddd', borderRadius: '4px', cursor: 'pointer', backgroundColor: '#fff' },
};
