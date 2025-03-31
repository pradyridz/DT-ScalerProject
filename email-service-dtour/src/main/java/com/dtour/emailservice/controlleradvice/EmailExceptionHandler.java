package com.dtour.emailservice.controlleradvice;

import com.dtour.emailservice.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class EmailExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleMessagingException(Exception ex, WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
