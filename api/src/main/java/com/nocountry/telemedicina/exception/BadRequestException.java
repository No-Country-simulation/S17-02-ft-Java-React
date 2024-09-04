package com.nocountry.telemedicina.exception;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BadRequestException extends RuntimeException{
    private final Integer statusCode = 400;

    public BadRequestException(String message) {
        super(message);
    }
}
