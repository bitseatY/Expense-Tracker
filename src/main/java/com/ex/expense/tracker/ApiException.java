package com.ex.expense.tracker;

import java.time.LocalDateTime;

public class ApiException {
    private String message;
    private LocalDateTime instant;
    private int status;
    public ApiException(String message,int status){
        this.message=message;
        this.status=status;
        instant=LocalDateTime.now();

    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getInstant() {
        return instant;
    }
}
