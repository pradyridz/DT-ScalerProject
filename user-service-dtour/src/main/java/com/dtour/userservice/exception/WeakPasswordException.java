package com.dtour.userservice.exception;

public class WeakPasswordException extends Exception {

    public WeakPasswordException() {
        super("Password choosen is weak and prone to getting stolen. " +
                "Please choose a strong password. A strong password is the one with at least one numeric character\n" +
                "at least one lowercase letter\n" +
                "at least one uppercase letter\n" +
                "at least one special character (e.g., !@#$%^&*)\n" +
                "no whitespace\n" +
                "at least eight characters long");
    }
}
