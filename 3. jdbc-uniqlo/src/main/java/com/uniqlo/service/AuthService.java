package com.uniqlo.service;

import com.uniqlo.dao.UserDAO;
import com.uniqlo.model.User;
import com.uniqlo.util.PasswordHasher;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && PasswordHasher.checkPassword(password, user.getPasswordHash())) {
            return user;
        }
        return null;
    }

    public boolean register(User user, String plainPassword) {
        if (userDAO.findByEmail(user.getEmail()) != null) {
            return false;
        }
        user.setPasswordHash(PasswordHasher.hashPassword(plainPassword));
        return userDAO.insert(user) > 0;
    }
}
