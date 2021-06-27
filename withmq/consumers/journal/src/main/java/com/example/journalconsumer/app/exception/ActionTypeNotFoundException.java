package com.example.journalconsumer.app.exception;

import org.springframework.http.HttpStatus;

public class ActionTypeNotFoundException extends ObjectNotFoundException {
    public ActionTypeNotFoundException(int id) {
        super("Action not found by " + id, HttpStatus.BAD_REQUEST.value());
    }

    public ActionTypeNotFoundException(Throwable cause) {
        super(cause);
    }
}
