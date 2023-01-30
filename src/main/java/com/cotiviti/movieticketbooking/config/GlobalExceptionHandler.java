package com.cotiviti.movieticketbooking.config;

import com.cotiviti.movieticketbooking.dto.GlobalResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final Map<String, String> errors = new HashMap<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getField() + " " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getObjectName() + " " + error.getDefaultMessage());
        }
        return handleExceptionInternal(ex, errorResponse(errors.values().stream().findFirst().get(), errors), headers, status, request);
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(errorResponse(ex.getMessage().split(":")[0], null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<GlobalResponse> handleAuthenticationException(HttpServletRequest httpServletRequest, Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse(httpServletRequest.getAttribute("message") != null ? httpServletRequest.getAttribute("message").toString() : "Unauthorized", null));
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<?> handleAll(final Exception ex, final WebRequest request) {
        return ResponseEntity.of(Optional.of(errorResponse(ex.getMessage(), null)));
    }

    protected GlobalResponse errorResponse(String message, Object errors) {
        GlobalResponse<Object> globalApiResponse = new GlobalResponse<>();
        return globalApiResponse.failureResponse(message, errors);
    }

}
