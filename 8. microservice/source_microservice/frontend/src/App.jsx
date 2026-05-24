import React from 'react';
import { BrowserRouter, Routes, Route, Navigate, NavLink } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import LoginPage from './pages/LoginPage';
import ProductPage from './pages/ProductPage';
import UserPage from './pages/UserPage';

// Protected Route – chỉ cho phép truy cập khi đã đăng nhập
function ProtectedRoute({ children }) {
  const { isAuthenticated, loading } = useAuth();
  if (loading) return <div>Loading...</div>;
  return isAuthenticated ? children : <Navigate to="/login" replace />;
}

// Layout chính với sidebar
function MainLayout({ children }) {
  const { user, logout } = useAuth();

  return (
    <div style={{ display: 'flex', minHeight: '100vh' }}>
      {/* Sidebar */}
      <aside style={styles.sidebar}>
        <div style={styles.logo}>UNIQLO</div>
        <nav>
          <NavLink to="/products" style={({ isActive }) => ({
            ...styles.navLink,
            backgroundColor: isActive ? '#333' : 'transparent',
          })}>
            Sản phẩm
          </NavLink>
          <NavLink to="/users" style={({ isActive }) => ({
            ...styles.navLink,
            backgroundColor: isActive ? '#333' : 'transparent',
          })}>
            Người dùng
          </NavLink>
        </nav>
        <div style={styles.userInfo}>
          <p style={{ fontSize: '14px', color: '#ccc' }}>{user?.fullName}</p>
          <button onClick={logout} style={styles.logoutBtn}>Đăng xuất</button>
        </div>
      </aside>

      {/* Main content */}
      <main style={{ flex: 1, backgroundColor: '#f9f9f9' }}>
        {children}
      </main>
    </div>
  );
}

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route
            path="/products"
            element={
              <ProtectedRoute>
                <MainLayout><ProductPage /></MainLayout>
              </ProtectedRoute>
            }
          />
          <Route
            path="/users"
            element={
              <ProtectedRoute>
                <MainLayout><UserPage /></MainLayout>
              </ProtectedRoute>
            }
          />
          <Route path="/" element={<Navigate to="/products" replace />} />
          <Route path="*" element={<Navigate to="/products" replace />} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

const styles = {
  sidebar: {
    width: '220px',
    backgroundColor: '#1a1a1a',
    color: '#fff',
    display: 'flex',
    flexDirection: 'column',
    padding: '0',
  },
  logo: {
    padding: '24px 20px',
    fontSize: '22px',
    fontWeight: 'bold',
    letterSpacing: '4px',
    borderBottom: '1px solid #333',
  },
  navLink: {
    display: 'block',
    padding: '14px 20px',
    color: '#ccc',
    textDecoration: 'none',
    fontSize: '14px',
    transition: 'background 0.2s',
  },
  userInfo: {
    marginTop: 'auto',
    padding: '20px',
    borderTop: '1px solid #333',
  },
  logoutBtn: {
    marginTop: '8px',
    padding: '6px 12px',
    backgroundColor: 'transparent',
    color: '#ccc',
    border: '1px solid #555',
    borderRadius: '4px',
    cursor: 'pointer',
    fontSize: '13px',
  },
};

export default App;
