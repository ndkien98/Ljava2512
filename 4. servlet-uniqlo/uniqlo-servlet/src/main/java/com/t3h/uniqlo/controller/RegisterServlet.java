package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * RegisterServlet
 * GET  /register → hiển thị form đăng ký
 * POST /register → validate và lưu user mới
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Nếu đã login → redirect dashboard
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("loggedUser") != null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String fullName        = req.getParameter("fullName");
        String email           = req.getParameter("email");
        String password        = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String gender          = req.getParameter("gender");

        // Validation
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("errorMsg", "Vui lòng điền đầy đủ thông tin!");
            forwardBack(req, resp, fullName, email, gender);
            return;
        }
        if (!password.equals(confirmPassword)) {
            req.setAttribute("errorMsg", "Mật khẩu xác nhận không khớp!");
            forwardBack(req, resp, fullName, email, gender);
            return;
        }
        if (password.length() < 6) {
            req.setAttribute("errorMsg", "Mật khẩu phải có ít nhất 6 ký tự!");
            forwardBack(req, resp, fullName, email, gender);
            return;
        }

        int newId = userService.register(fullName, email, password, gender);
        if (newId == -1) {
            req.setAttribute("errorMsg", "Email đã được sử dụng! Vui lòng chọn email khác.");
            forwardBack(req, resp, fullName, email, gender);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/login?success=registered");
    }

    private void forwardBack(HttpServletRequest req, HttpServletResponse resp,
                             String fullName, String email, String gender)
            throws ServletException, IOException {
        req.setAttribute("fullName", fullName);
        req.setAttribute("email", email);
        req.setAttribute("gender", gender);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/auth/register.jsp")
                .forward(req, resp);
    }
}
