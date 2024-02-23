package com.kbtg.bootcamp.posttest.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LotteryRequest {
    @NotEmpty(message = "Ticket number is required")
    @Pattern(regexp = "^\\d{6}", message = "Ticket number must be 6 digits string")
    private String ticket;

    @NotNull(message = "Ticket price is required")
    @Min(value = 1, message = "Ticket price must be greater than zero")
    private Double price;

    @NotNull(message = "Ticket amount is required")
    @Min(value = 1, message = "Ticket amount must be greater than zero")
    private Integer amount;
}
