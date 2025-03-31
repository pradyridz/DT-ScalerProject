package com.dtour.emailservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ExceptionDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ExceptionDetails(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
