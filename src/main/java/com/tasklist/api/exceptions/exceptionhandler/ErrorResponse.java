package com.tasklist.api.exceptions.exceptionhandler;

public class ErrorResponse {

    private String message;
    private String reason;

    public ErrorResponse(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }
}
