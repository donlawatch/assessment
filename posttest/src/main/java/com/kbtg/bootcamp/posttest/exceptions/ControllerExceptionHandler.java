package com.kbtg.bootcamp.posttest.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestException(MethodArgumentNotValidException notValidException, WebRequest request) {
        List<String> error = notValidException
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        if (error.size() > 1) {
            return new ErrorMessage(
                    HttpStatus.BAD_REQUEST.value(),
                    LocalDate.now(),
                    error,
                    request.getDescription(false)
            );
        }

        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now(),
                String.join("", error),
                request.getDescription(false)
        );
    }
}
