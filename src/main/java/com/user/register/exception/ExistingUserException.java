package com.user.register.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExistingUserException extends RuntimeException {
    public ExistingUserException(String message){
        super(message);
    }
}
