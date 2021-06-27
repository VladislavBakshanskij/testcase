package com.example.testcase.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResolver {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAll(final Exception e) {
        final ErrorMessage errMsg = new ErrorMessage().setMessage(e.getMessage());
        errMsg.getErrors().put("error", String.valueOf(e));
        return errMsg;
    }
}
