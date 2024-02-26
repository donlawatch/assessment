package com.kbtg.bootcamp.posttest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.bootcamp.posttest.dtos.requests.LotteryRequest;
import com.kbtg.bootcamp.posttest.dtos.responses.LotteryResponse;
import com.kbtg.bootcamp.posttest.dtos.responses.PurchaseListResponse;
import com.kbtg.bootcamp.posttest.dtos.responses.PurchaseTicketResponse;
import com.kbtg.bootcamp.posttest.services.LotteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LotteryControllerTest {
    MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController).build();
    }

    @Test
    @DisplayName("When perform on GET: /lotteries, then return ticket '123456' and '654321'")
    void getTickets() throws Exception {
        //Given
        List<String> tickets = List.of("123456", "654321");
        LotteryResponse response = new LotteryResponse(tickets);

        //When
        when(lotteryService.getTickets()).thenReturn(response);

        //Then
        mockMvc.perform(get("/lotteries"))
                .andExpect(jsonPath("$.tickets[0]", is("123456")))
                .andExpect(jsonPath("$.tickets[1]", is("654321")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("When perform on GET: /users/{userId}/lotteries, then return a purchase list")
    void getPurchaseList() throws Exception {
        //Given
        List<String> tickets = List.of("456789", "224236");
        PurchaseListResponse response = new PurchaseListResponse(tickets, 2, 100.0);

        //When
        when(lotteryService.getPurchaseList(any())).thenReturn(response);

        mockMvc.perform(get("/users/0123456789/lotteries"))
                .andExpect(jsonPath("$.tickets", is(tickets)))
                .andExpect(jsonPath("$.count", is(2)))
                .andExpect(jsonPath("$.price", is(100.0)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("When perform on POST: /admin/lotteries with request bodo, then return ticket ID 123456")
    void addTicket() throws Exception{
        //Given
        LotteryRequest request = new LotteryRequest();
        request.setTicket("123456");
        request.setPrice(100.0);
        request.setAmount(1);
        String json = new ObjectMapper().writeValueAsString(request);
        LotteryResponse response = new LotteryResponse("123456");

        //When
        when(lotteryService.addTicket(any())).thenReturn(response);

        //Then
        mockMvc.perform(
                post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket", is("123456")));

    }

    @Test
    @DisplayName("When perform POST: /users/:userId/lotteries/:ticketId, then return user ticket ID")
    void purchaseTicket() throws Exception {
        //Given
        PurchaseTicketResponse response = new PurchaseTicketResponse("1");

        //When
        when(lotteryService.purchaseTicket(any(), any())).thenReturn(response);

        //Then
        mockMvc.perform(post("/users/0123456789/lotteries/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")));
    }

    @Test
    @DisplayName("When perform on DELETE: /users/:userId/lotteries/:ticketId, then return ticket ID 000111")
    void sellBackTicket() throws Exception {
        //Given
        LotteryResponse response = new LotteryResponse("000111");

        //When
        when(lotteryService.sellBackTicket(any(), any())).thenReturn(response);

        //Then
        mockMvc.perform(delete("/users/0123456789/lotteries/000111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket", is("000111")));
    }
}