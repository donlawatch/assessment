package com.kbtg.bootcamp.posttest.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private final int statusCode;
    private final LocalDate timestamp;
    private String error;
    private List<String> errors;
    private final String description;

    public ErrorMessage(int statusCode, LocalDate timestamp, String error, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.error = error;
        this.description = description;
    }

    public ErrorMessage(int statusCode, LocalDate timestamp, List<String> errors, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.errors = errors;
        this.description = description;
    }

}
