package com.nocountry.telemedicina.exception;

import com.nocountry.telemedicina.security.oauth2.exception.OAuth2AuthenticationProcessingException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), 500, ex.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customhandleException(CustomException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(DatabaseDuplicateException.class)
    public ResponseEntity<ErrorResponse> databaseDuplicatehandleException(DatabaseDuplicateException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponse> internalServerhandleException(InternalServerException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorResponse> notAuthorizedhandleException(NotAuthorizedException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> NotFoundhandleException(NotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestException(BadRequestException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> invalidCredentialsException(InvalidCredentialsException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> invalidCredentialsException(CustomException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(OAuth2AuthenticationProcessingException.class)
    public ResponseEntity<ErrorResponse> oAuth2AuthenticationProcessingException(
            OAuth2AuthenticationProcessingException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getDescription(false), ex.getStatusCode(),
                ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerValidationExceptions(MethodArgumentNotValidException ex,
            WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = (error instanceof FieldError) ? ((FieldError) error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("statusCode",400);
        errors.put("details",request.getDescription(false));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
            WebRequest request) {
        @SuppressWarnings("null")
        String errorMessage = String.format("Failed to convert value '%s' to required type '%s'.",
                ex.getValue(), ex.getRequiredType().getSimpleName());
        ErrorResponse error = new ErrorResponse(request.getDescription(false), 400, errorMessage);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
