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
    private String message;
    private List<String> messages;
    private final String description;

    public ErrorMessage(int statusCode, LocalDate timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public ErrorMessage(int statusCode, LocalDate timestamp, List<String> messages, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.messages = messages;
        this.description = description;
    }

}
