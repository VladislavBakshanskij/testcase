package com.example.journalconsumer.app.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends DomainException {
    public ObjectNotFoundException(String objName, long id) {
        this(objName, String.valueOf(id));
    }

    public ObjectNotFoundException(String objName, String id) {
        super(String.format("%s not found by %s", objName, id), HttpStatus.NOT_FOUND.value());
    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }
}
