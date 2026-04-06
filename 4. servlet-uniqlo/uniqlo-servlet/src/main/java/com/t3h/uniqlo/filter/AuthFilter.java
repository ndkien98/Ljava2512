package com.t3h.uniqlo.filter;

import com.t3h.uniqlo.model.dto.UserDTO;
import com.t3h.uniqlo.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * AuthFilter – Java Annotation Config
 * Bảo vệ các route yêu cầu đăng nhập.
 * Nếu chưa login → kiểm tra cookie remember_token → auto-login hoặc redirect /login
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/productions", "/dashboard"})
public class AuthFilter implements Filter {

    private final UserService userService = new UserService();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req  = (HttpServletRequest)  request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);// false tức là nếu trừờng hợp chưa có session nào thì trả về null, không tạo mới session

        UserDTO loggedUser = (session != null) ? (UserDTO) session.getAttribute("loggedUser") : null;

        // Đã login → cho qua
        if (loggedUser != null) {
            chain.doFilter(request, response); // tiếp tục chuỗi filter → đến servlet bình thường cho request đã login
            return;
        }

        // Chưa login → kiểm tra cookie remember_token
        String token = getCookieValue(req, "remember_token");
        if (token != null) {
            UserDTO userFromToken = userService.findByToken(token);
            if (userFromToken != null) {
                // Auto-login: tạo session mới
                HttpSession newSession = req.getSession(true);
                newSession.setAttribute("loggedUser", userFromToken);
                chain.doFilter(request, response);
                return;
            }
        }

        // Không có quyền → redirect về trang login
        String loginUrl = req.getContextPath() + "/login"; // localhost:8080/login
        resp.sendRedirect(loginUrl + "?redirect=" + req.getServletPath());
    }

    private String getCookieValue(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) return c.getValue();
        }
        return null;
    }
}
