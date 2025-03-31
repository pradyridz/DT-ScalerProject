package com.dtour.userservice.exception;

public class RequiredFieldNullException extends Exception {

    public RequiredFieldNullException(String fieldName) {
        super("Required field cannot be set to null/empty: " + fieldName);
    }

}
