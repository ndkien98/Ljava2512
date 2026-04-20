package com.t3h.uniqlo.constant;

public final class ErrorCodes {
    
    public static final String CATEGORY_NOT_FOUND = "CAT_001";
    public static final String PRODUCT_NOT_FOUND = "PRD_001";
    public static final String USER_NOT_FOUND = "USR_001";
    public static final String UNAUTHORIZED_ACCESS = "AUTH_001";
    public static final String VALIDATION_FAILED = "VAL_001";
    public static final String INTERNAL_SERVER_ERROR = "SYS_001";
    
    private ErrorCodes() {
        throw new UnsupportedOperationException("Utility class");
    }
}
