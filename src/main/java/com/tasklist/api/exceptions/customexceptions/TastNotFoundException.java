package com.tasklist.api.exceptions.customexceptions;

import com.tasklist.api.exceptions.exceptionhandler.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TastNotFoundException extends BusinessException {
    public TastNotFoundException(String message) {
        super(message);
    }
}
