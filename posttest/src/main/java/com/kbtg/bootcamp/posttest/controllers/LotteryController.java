package com.kbtg.bootcamp.posttest.controllers;

import com.kbtg.bootcamp.posttest.dtos.requests.LotteryRequest;
import com.kbtg.bootcamp.posttest.dtos.responses.LotteryResponse;
import com.kbtg.bootcamp.posttest.services.LotteryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryController {
    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    //TODO: Add basic authentication to addTicket
    @PostMapping("/admin/lotteries")
    public LotteryResponse addTicket(@Valid @RequestBody LotteryRequest lotteryRequest) {
        return lotteryService.addTicket(lotteryRequest);
    }

    //TODO: Add validation
    @GetMapping("/lotteries")
    public LotteryResponse getTickets() {
        return lotteryService.getTickets();
    };
}

