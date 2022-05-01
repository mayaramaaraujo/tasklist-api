package com.tasklist.api.exceptions.exceptionhandler;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
