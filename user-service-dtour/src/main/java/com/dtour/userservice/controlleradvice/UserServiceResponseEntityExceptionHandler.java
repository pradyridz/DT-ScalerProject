package com.dtour.userservice.controlleradvice;

import com.dtour.userservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserServiceResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IntegerLengthException.class)
    public ResponseEntity<ExceptionDetails> handleIntegerLengthException(IntegerLengthException ex, WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<ExceptionDetails> handleWeakPasswordException(WeakPasswordException ex, WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequiredFieldNullException.class)
    public ResponseEntity<ExceptionDetails> handleMissingFieldException(RequiredFieldNullException ex, WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataformatException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidDataformatException(InvalidDataformatException ex, WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(S3FileIOException.class)
    public ResponseEntity<ExceptionDetails> handleS3FileIOException(S3FileIOException ex, WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(EmailNotificationException.class)
    public ResponseEntity<ExceptionDetails> handleEmailNotificationException(EmailNotificationException ex, WebRequest request) {
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CREATED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleRuntimeException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionDetails errorDetails = new ExceptionDetails(LocalDateTime.now(),
                "Server ran into unexpected error. Please contact admin", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
