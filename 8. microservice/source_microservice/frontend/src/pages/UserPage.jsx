import React, { useEffect, useState } from 'react';
import { getUsers } from '../api/userApi';

export default function UserPage() {
  const [users, setUsers] = useState([]);
  const [keyword, setKeyword] = useState('');
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  useEffect(() => { loadUsers(); }, [page]);

  const loadUsers = async () => {
    setLoading(true);
    try {
      const params = { page, size: 10 };
      if (keyword) params.keyword = keyword;
      const res = await getUsers(params);
      setUsers(res.data.content);
      setTotalPages(res.data.totalPages);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: '24px' }}>
      <h2 style={{ fontSize: '24px', marginBottom: '20px' }}>Quản lý người dùng</h2>

      <div style={{ display: 'flex', gap: '12px', marginBottom: '20px' }}>
        <input
          type="text"
          value={keyword}
          onChange={e => setKeyword(e.target.value)}
          placeholder="Tìm kiếm theo tên, email..."
          style={{ flex: 1, padding: '8px 12px', border: '1px solid #ddd', borderRadius: '4px' }}
        />
        <button
          onClick={() => { setPage(0); loadUsers(); }}
          style={{ padding: '8px 20px', backgroundColor: '#000', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
        >
          Tìm kiếm
        </button>
      </div>

      {loading ? <p>Đang tải...</p> : (
        <table style={{ width: '100%', borderCollapse: 'collapse' }}>
          <thead>
            <tr style={{ backgroundColor: '#f5f5f5' }}>
              <th style={{ padding: '12px', textAlign: 'left' }}>ID</th>
              <th style={{ padding: '12px', textAlign: 'left' }}>Họ tên</th>
              <th style={{ padding: '12px', textAlign: 'left' }}>Email</th>
              <th style={{ padding: '12px', textAlign: 'left' }}>Giới tính</th>
              <th style={{ padding: '12px', textAlign: 'left' }}>Vai trò</th>
            </tr>
          </thead>
          <tbody>
            {users.map(user => (
              <tr key={user.id} style={{ borderBottom: '1px solid #eee' }}>
                <td style={{ padding: '12px' }}>{user.id}</td>
                <td style={{ padding: '12px', fontWeight: '500' }}>{user.fullName}</td>
                <td style={{ padding: '12px' }}>{user.email}</td>
                <td style={{ padding: '12px' }}>{user.gender}</td>
                <td style={{ padding: '12px' }}>
                  {user.roles?.map(r => (
                    <span key={r} style={{ backgroundColor: '#e3f2fd', padding: '2px 8px', borderRadius: '12px', fontSize: '12px', marginRight: '4px' }}>
                      {r}
                    </span>
                  ))}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', marginTop: '24px' }}>
        <button onClick={() => setPage(p => Math.max(0, p - 1))} disabled={page === 0}
          style={{ padding: '8px 16px', border: '1px solid #ddd', borderRadius: '4px', cursor: 'pointer' }}>
          ← Trước
        </button>
        <span style={{ margin: '0 16px' }}>Trang {page + 1} / {totalPages}</span>
        <button onClick={() => setPage(p => p + 1)} disabled={page >= totalPages - 1}
          style={{ padding: '8px 16px', border: '1px solid #ddd', borderRadius: '4px', cursor: 'pointer' }}>
          Sau →
        </button>
      </div>
    </div>
  );
}
