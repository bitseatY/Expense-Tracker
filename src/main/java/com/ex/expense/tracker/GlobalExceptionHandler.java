package com.ex.expense.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> handleCategoryNotFoundException(ResourceNotFoundException e){
        ApiException apiException=new ApiException(e.getMessage(),400);
        return ResponseEntity.badRequest().body(apiException);
    }
}
