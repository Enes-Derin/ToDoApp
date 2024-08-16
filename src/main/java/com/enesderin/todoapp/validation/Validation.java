package com.enesderin.todoapp.validation;

import com.enesderin.todoapp.core.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class Validation {

    @ExceptionHandler({MethodArgumentNotValidException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> validate(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setStatus(400);
        apiError.setPath("/v1");
        Map<String, String> validationErrors = new HashMap<>();
        for (var fieldError:exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        apiError.setValidations(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }
}
