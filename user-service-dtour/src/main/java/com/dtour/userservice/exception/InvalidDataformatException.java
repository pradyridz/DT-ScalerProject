package com.dtour.userservice.exception;

public class InvalidDataformatException extends Exception {
    public InvalidDataformatException(String emailAddress) {
        super("Invalid data format for field "+emailAddress);
    }
}
