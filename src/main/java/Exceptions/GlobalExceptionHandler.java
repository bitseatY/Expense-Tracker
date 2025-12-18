package Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> handleCategoryNotFoundException(ResourceNotFoundException e){
        ApiException apiException=new ApiException(e.getMessage(),400);
        return ResponseEntity.badRequest().body(apiException);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiException> handleTypeMismatchException(MethodArgumentTypeMismatchException e){
        ApiException apiException=new ApiException(e.getName() +" must be of type "+e.getRequiredType().getSimpleName(),400);
        return  ResponseEntity.badRequest().body(apiException);
    }
}
