package com.ex.expense.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiException> handleCategoryNotFoundException(CategoryNotFoundException e){
        ApiException apiException=new ApiException(e.getMessage(),400);
        return ResponseEntity.badRequest().body(apiException);
    }
}
