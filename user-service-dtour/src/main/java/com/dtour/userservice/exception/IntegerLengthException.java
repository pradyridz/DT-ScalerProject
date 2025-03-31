package com.dtour.userservice.exception;

public class IntegerLengthException extends Exception {

    public IntegerLengthException(String fieldName, int expectedLength) {
        super("Invalid length for field " + fieldName + ". Expected length: " + expectedLength);
    }

}
