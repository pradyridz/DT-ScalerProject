package com.dtour.emailservice.exception;

public class InvalidVerificationTokenException extends Throwable {
    public InvalidVerificationTokenException(String msg) {
        super(msg);
    }
}
