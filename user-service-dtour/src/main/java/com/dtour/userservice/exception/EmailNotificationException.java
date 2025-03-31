package com.dtour.userservice.exception;

public class EmailNotificationException extends Exception {

    public EmailNotificationException() {
        super("Email notification failed for the event but data processed successfully");
    }

    public EmailNotificationException(Throwable cause) {
        super("Email notification failed for the event but data processed successfully",cause);
    }
}
