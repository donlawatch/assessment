package com.kbtg.bootcamp.posttest.dtos.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LotteryResponse {
    private String ticket;
    private List<String> tickets;

    public LotteryResponse(String ticket) {
        this.ticket = ticket;
    }

    public LotteryResponse(List<String> tickets) {
        this.tickets = tickets;
    }
}


