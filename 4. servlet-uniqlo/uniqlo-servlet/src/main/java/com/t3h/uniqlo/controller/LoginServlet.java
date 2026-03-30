package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.model.dto.UserDTO;
import com.t3h.uniqlo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.UUID;

/**
 * LoginServlet
 * GET  /login → hiển thị form đăng nhập
 * POST /login → xử lý đăng nhập, set Session, Cookie (Remember Me)
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Nếu đã login → không cần vào trang login
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("loggedUser") != null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/auth/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email       = req.getParameter("email");
        String password    = req.getParameter("password");
        String rememberMe  = req.getParameter("rememberMe"); // mã xác định có ghi nhớ đăng nhập hay không, nếu có tích -> xác định có ghi nhớ đăng nhập, tạo ra cookie lưu token để auto-login lần sau, nếu không tích -> không tạo cookie
        String redirectUrl = req.getParameter("redirect");

        UserDTO user = userService.login(email, password);

        if (user == null) {
            req.setAttribute("errorMsg", "Email hoặc mật khẩu không đúng!");
            req.setAttribute("email", email);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/auth/login.jsp")
                    .forward(req, resp);
            return;
        }

        // Tạo session
        HttpSession session = req.getSession(true);// lấy ra session hiện tại, nếu chưa có session nào thì tạo mới
        session.setAttribute("loggedUser", user);
        session.setMaxInactiveInterval(30 * 60); // 30 phút

        // Remember Me → lưu token vào cookie (7 ngày)
        if ("on".equals(rememberMe)) {
            String token = UUID.randomUUID().toString();
            userService.saveRememberToken(user.getId(), token);

            Cookie cookie = new Cookie("remember_token", token);
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            resp.addCookie(cookie);

            // Lưu thêm cookie email để pre-fill form
            Cookie emailCookie = new Cookie("saved_email", email);
            emailCookie.setMaxAge(7 * 24 * 60 * 60);
            emailCookie.setPath("/");
            resp.addCookie(emailCookie);
        }

        // Redirect
        if (redirectUrl != null && !redirectUrl.isBlank()) {
            resp.sendRedirect(req.getContextPath() + redirectUrl);
        } else {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        }
    }
}
