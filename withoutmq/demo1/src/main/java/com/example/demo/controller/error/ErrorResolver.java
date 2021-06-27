package com.example.demo.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.app.exception.ActionTypeNotFoundException;
import com.example.demo.app.exception.DomainException;
import com.example.demo.app.exception.ObjectNotFoundException;

import java.util.Optional;

@RestControllerAdvice
public class ErrorResolver {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAll(final Exception e) {
        e.printStackTrace();
        return new ErrorMessage()
                .setMessage("Internal server error");
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> onDomainException(final DomainException e) {
        ErrorMessage errMsg = new ErrorMessage().setMessage(e.getMessage());
        return new ResponseEntity<>(errMsg, Optional.ofNullable(HttpStatus.resolve(e.getCode()))
                .orElse(HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage onObjectNotFoundException(final ObjectNotFoundException e) {
        return new ErrorMessage().setMessage(e.getMessage());
    }

    @ExceptionHandler(ActionTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage onActionTypeNotFoundException(final ActionTypeNotFoundException e) {
        return new ErrorMessage().setMessage(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage onMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final ErrorMessage error = new ErrorMessage();
        error.setMessage("Validation error");
        for (FieldError er : e.getFieldErrors()) {
            error.getErrors().put(er.getField(), er.getDefaultMessage());
        }
        return error;
    }
}
