package com.kbtg.bootcamp.posttest.controllers;

import com.kbtg.bootcamp.posttest.dtos.requests.LotteryRequest;
import com.kbtg.bootcamp.posttest.dtos.responses.LotteryResponse;
import com.kbtg.bootcamp.posttest.dtos.responses.PurchaseListResponse;
import com.kbtg.bootcamp.posttest.dtos.responses.PurchaseTicketResponse;
import com.kbtg.bootcamp.posttest.services.LotteryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class LotteryController {
    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }
    @GetMapping("/lotteries")
    public LotteryResponse getTickets() {
        return lotteryService.getTickets();
    }

    @GetMapping("/users/{userId}/lotteries")
    public PurchaseListResponse getPurchaseList(
            @PathVariable("userId") @Pattern(regexp = "^\\d{10}$", message = "User ID must be 10 digits number") String userId
    ) {
        return lotteryService.getPurchaseList(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/lotteries")
    public LotteryResponse addTicket(@Valid @RequestBody LotteryRequest lotteryRequest) {
        return lotteryService.addTicket(lotteryRequest);
    }


    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public PurchaseTicketResponse purchaseTicket(
            @PathVariable("userId") @Pattern(regexp = "^\\d{10}$", message = "User ID must be 10 digits number") String userId,
            @PathVariable("ticketId") @Pattern(regexp = "^\\d{6}$", message = "Ticket ID must be 6 digits number") String ticketId
    ) {
        return lotteryService.purchaseTicket(userId, ticketId);
    }

    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public LotteryResponse sellBackTicket(
            @PathVariable("userId") @Pattern(regexp = "^\\d{10}$", message = "User ID must be 10 digits number") String userId,
            @PathVariable("ticketId") @Pattern(regexp = "^\\d{6}$", message = "Ticket ID must be 6 digits number") String ticketId
    ) {
       return lotteryService.sellBackTicket(userId, ticketId);
    }
}

