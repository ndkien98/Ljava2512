import React, { createContext, useContext, useState, useEffect } from 'react';
import { login as loginApi, logout as logoutApi } from '../api/authApi';

/**
 * AuthContext – quản lý trạng thái xác thực toàn ứng dụng
 *
 * Cung cấp:
 * - user: thông tin user hiện tại (null nếu chưa đăng nhập)
 * - login(email, password): đăng nhập
 * - logout(): đăng xuất
 * - isAuthenticated: boolean
 */
const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Kiểm tra token có sẵn khi load app
  useEffect(() => {
    const token = localStorage.getItem('access_token');
    const savedUser = localStorage.getItem('user_info');
    if (token && savedUser) {
      try {
        setUser(JSON.parse(savedUser));
      } catch {
        localStorage.clear();
      }
    }
    setLoading(false);
  }, []);

  const login = async (email, password) => {
    const response = await loginApi(email, password);
    const { access_token, refresh_token, ...userInfo } = response.data;

    localStorage.setItem('access_token', access_token);
    localStorage.setItem('refresh_token', refresh_token);
    localStorage.setItem('user_info', JSON.stringify(userInfo));

    setUser(userInfo);
    return userInfo;
  };

  const logout = () => {
    logoutApi();
    localStorage.removeItem('user_info');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{
      user,
      login,
      logout,
      isAuthenticated: !!user,
      loading,
    }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth must be used within AuthProvider');
  return context;
};
