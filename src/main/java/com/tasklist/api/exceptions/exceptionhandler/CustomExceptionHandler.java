package com.tasklist.api.exceptions.exceptionhandler;

import com.tasklist.api.exceptions.customexceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handlePayloadNotValid(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid body", e.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidUserException.class)
    protected ResponseEntity<Object> handleInvalidUser(InvalidUserException e) {
        ErrorResponse errorResponse = new ErrorResponse("User not found", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
