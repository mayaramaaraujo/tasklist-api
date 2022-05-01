package com.tasklist.api.exceptions.customexceptions;

import com.tasklist.api.exceptions.exceptionhandler.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPayloadException extends BusinessException {
    public InvalidPayloadException(String message) {
        super(message);
    }
}
