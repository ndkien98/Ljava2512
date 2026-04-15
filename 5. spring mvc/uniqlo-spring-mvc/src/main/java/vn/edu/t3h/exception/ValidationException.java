package vn.edu.t3h.exception;

import java.util.Collections;
import java.util.Map;

public class ValidationException extends RuntimeException {
    private final Map<String, String> errors;

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors == null ? Collections.emptyMap() : errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

