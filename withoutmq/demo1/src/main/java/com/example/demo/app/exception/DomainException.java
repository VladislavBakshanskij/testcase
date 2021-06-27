package com.example.demo.app.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private int code;

    public DomainException(String message, int code) {
        super(message);
        this.code = code;
    }

    public DomainException(Throwable cause) {
        super(cause);
    }
}