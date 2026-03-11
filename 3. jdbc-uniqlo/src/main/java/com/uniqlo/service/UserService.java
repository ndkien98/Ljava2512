package com.uniqlo.service;

import com.uniqlo.dao.UserDAO;
import com.uniqlo.model.User;
import java.util.List;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public boolean createUser(User user) {
        // Assume password is already hashed or handled by AuthService
        return userDAO.insert(user) > 0;
    }

    public boolean updateUser(User user) {
        return userDAO.update(user) > 0;
    }

    public boolean deleteUser(int id) {
        return userDAO.delete(id) > 0;
    }
}
