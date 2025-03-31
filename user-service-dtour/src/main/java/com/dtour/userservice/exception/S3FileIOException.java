package com.dtour.userservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class S3FileIOException extends Exception {
    public S3FileIOException(String msg,Throwable cause) {
        super(msg,cause);
    }
}
