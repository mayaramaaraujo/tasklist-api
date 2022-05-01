package com.tasklist.api.exceptions.customexceptions;

import com.tasklist.api.exceptions.exceptionhandler.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends BusinessException {
    public UserExistsException(String message) {
        super(message);
    }
}
