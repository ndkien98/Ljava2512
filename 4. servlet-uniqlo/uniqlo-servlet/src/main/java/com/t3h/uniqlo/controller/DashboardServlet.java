package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.model.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * DashboardServlet
 * GET /dashboard → trang chào mừng sau khi đăng nhập (protected by AuthFilter)
 */
@WebServlet(name = "DashboardServlet", urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserDTO user = (session != null) ? (UserDTO) session.getAttribute("loggedUser") : null;

        // Thông tin session
        if (session != null) {
            req.setAttribute("sessionId",       session.getId());
            req.setAttribute("sessionCreated",  new java.util.Date(session.getCreationTime()).toString());
            req.setAttribute("sessionAccessed", new java.util.Date(session.getLastAccessedTime()).toString());
            req.setAttribute("sessionTimeout",  session.getMaxInactiveInterval() / 60);
        }

        // Format createdAt thành String để JSP không cần xử lý LocalDateTime
        if (user != null && user.getCreatedAt() != null) {
            req.setAttribute("createdAtStr", user.getCreatedAt().format(FMT));
        }

        // Avatar initial (first char of name)
        if (user != null && user.getFullName() != null && !user.getFullName().isEmpty()) {
            req.setAttribute("avatarInitial", String.valueOf(user.getFullName().charAt(0)).toUpperCase());
        } else {
            req.setAttribute("avatarInitial", "U");
        }

        // Cookies
        req.setAttribute("cookies", req.getCookies());

        // Visit count từ ServletContext
        Object vc = getServletContext().getAttribute("visitCount");
        req.setAttribute("visitCount", vc != null ? vc : 0L);

        req.setAttribute("user", user);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/auth/dashboard.jsp")
                .forward(req, resp);
    }
}

