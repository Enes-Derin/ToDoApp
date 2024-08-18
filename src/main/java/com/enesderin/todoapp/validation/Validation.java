package com.enesderin.todoapp.validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
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
