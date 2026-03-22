package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.model.dto.UserDTO;
import com.t3h.uniqlo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * LogoutServlet
 * GET /logout → xóa session, xóa cookie remember_token, redirect /login
 */
@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            UserDTO user = (UserDTO) session.getAttribute("loggedUser");
            if (user != null) {
                // Xóa remember token trong DB
                userService.clearRememberToken(user.getId());
            }
            session.invalidate();
        }

        // Xóa cookie remember_token (max age = 0 là xóa cookie)
        Cookie tokenCookie = new Cookie("remember_token", "");
        tokenCookie.setMaxAge(0);
        tokenCookie.setPath("/");
        resp.addCookie(tokenCookie);

        // Xóa cookie saved_email
        Cookie emailCookie = new Cookie("saved_email", "");
        emailCookie.setMaxAge(0);
        emailCookie.setPath("/");
        resp.addCookie(emailCookie);

        resp.sendRedirect(req.getContextPath() + "/login?success=loggedout");
    }
}
