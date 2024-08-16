package com.enesderin.todoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundException extends RuntimeException {

    @ExceptionHandler(NotFoundException.class)
    public static ResponseEntity<String> notFoundException() {
        return new ResponseEntity<>("Not Found To Do", HttpStatus.NOT_FOUND);
    }
}
