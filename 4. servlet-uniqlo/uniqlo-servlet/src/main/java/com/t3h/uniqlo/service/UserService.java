package com.t3h.uniqlo.service;

import com.t3h.uniqlo.dao.UserDao;
import com.t3h.uniqlo.model.dto.UserDTO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    private final UserDao userDao = new UserDao();

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32) hexString.insert(0, '0');
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Đăng nhập: tìm user theo email + MD5(password)
     */
    public UserDTO login(String email, String rawPassword) {
        if (email == null || rawPassword == null || email.isBlank() || rawPassword.isBlank()) {
            return null;
        }
        String hashed = md5(rawPassword.trim());
        return userDao.findByEmailAndPassword(email.trim().toLowerCase(), hashed);
    }

    /**
     * Đăng ký: kiểm tra email tồn tại, hash password rồi lưu
     * @return GeneratedKey của user mới, -1 nếu email đã tồn tại
     */
    public int register(String fullName, String email, String rawPassword, String gender) {
        if (email == null || email.isBlank()) return -1;
        // Kiểm tra email đã tồn tại chưa
        UserDTO existing = userDao.findByEmail(email.trim().toLowerCase());
        if (existing != null) return -1;

        UserDTO user = UserDTO.builder()
                .fullName(fullName != null ? fullName.trim() : "")
                .email(email.trim().toLowerCase())
                .passwordHash(md5(rawPassword))
                .gender(gender)
                .role("USER")
                .build();
        return userDao.save(user);
    }

    public UserDTO findByToken(String token) {
        if (token == null || token.isBlank()) return null;
        return userDao.findByRememberToken(token);
    }

    public void saveRememberToken(int userId, String token) {
        userDao.updateRememberToken(userId, token);
    }

    public void clearRememberToken(int userId) {
        userDao.clearRememberToken(userId);
    }
}
