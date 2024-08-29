package com.nocountry.telemedicina.security.oauth2.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OAuth2AuthenticationProcessingException extends RuntimeException {
    private final Integer statusCode = 400;

    public OAuth2AuthenticationProcessingException(String message) {
        super(message);
    }
}
