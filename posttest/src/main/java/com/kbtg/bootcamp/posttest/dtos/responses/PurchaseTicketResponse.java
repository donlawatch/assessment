package com.kbtg.bootcamp.posttest.dtos.responses;

import lombok.Data;


@Data
public class PurchaseTicketResponse {
    private String id;

    public PurchaseTicketResponse(String id) {
        this.id = id;
    }
}
