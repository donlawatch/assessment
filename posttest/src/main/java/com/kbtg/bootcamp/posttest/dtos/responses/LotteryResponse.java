package com.kbtg.bootcamp.posttest.dtos.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LotteryResponse {
    private String ticket;
    private List<String> tickets;
}


