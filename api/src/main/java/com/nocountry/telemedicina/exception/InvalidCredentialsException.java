package com.nocountry.telemedicina.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidCredentialsException extends RuntimeException{
    private final Integer statusCode = 403;

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
