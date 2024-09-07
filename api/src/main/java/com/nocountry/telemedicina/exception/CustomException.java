package com.nocountry.telemedicina.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

public class CustomException extends RuntimeException {
    private Integer statusCode;
    public CustomException(Integer statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
