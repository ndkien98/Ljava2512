package com.uniqlo.util;

/**
 * Simple password hasher utility. 
 * Note: In a real app, use BCrypt. For this demo, we'll use a simple mock or 
 * a basic message digest if libraries are restricted, but since I added 
 * no specific security library, I will use a simple one for now.
 * Wait, I should probably use a real one. Use String.hashCode is NOT secure.
 * I will use a basic SHA-256 for now.
 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkPassword(String plain, String hashed) {
        return hashPassword(plain).equals(hashed);
    }
}
