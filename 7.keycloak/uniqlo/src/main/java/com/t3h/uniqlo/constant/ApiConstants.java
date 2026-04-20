package com.t3h.uniqlo.constant;

public final class ApiConstants {
    
    public static final String API_VERSION = "/api/v1";
    
    public static final String CATEGORIES = API_VERSION + "/categories";
    public static final String PRODUCTS = API_VERSION + "/products";
    public static final String USERS = API_VERSION + "/users";
    public static final String AUTH = API_VERSION + "/auth";
    
    private ApiConstants() {
        throw new UnsupportedOperationException("Utility class");
    }
}
