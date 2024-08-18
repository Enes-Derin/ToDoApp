package com.enesderin.todoapp.exception;



public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
