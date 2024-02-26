package com.kbtg.bootcamp.posttest.services;

import com.kbtg.bootcamp.posttest.dtos.requests.LotteryRequest;
import com.kbtg.bootcamp.posttest.dtos.responses.LotteryResponse;
import com.kbtg.bootcamp.posttest.dtos.responses.PurchaseListResponse;
import com.kbtg.bootcamp.posttest.dtos.responses.PurchaseTicketResponse;
import com.kbtg.bootcamp.posttest.models.Lottery;
import com.kbtg.bootcamp.posttest.models.UserTicket;
import com.kbtg.bootcamp.posttest.repositories.LotteryRepository;
import com.kbtg.bootcamp.posttest.repositories.UserTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LotteryServiceTest {
    @Mock
    private LotteryRepository lotteryRepository;

    @Mock
    private UserTicketRepository userTicketRepository;

    LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lotteryService = spy(new LotteryService(lotteryRepository, userTicketRepository));
    }

    @Test
    @DisplayName("Should return ticket ID 123456 when add ticket successfully")
    void addTicket() {
        //Given
        LotteryResponse response = new LotteryResponse("123456");
        LotteryRequest lotteryReq = new LotteryRequest();

        lotteryReq.setTicket("123456");
        lotteryReq.setAmount(1);
        lotteryReq.setPrice(100.0);

        //When
        when(lotteryService.addTicket(lotteryReq)).thenReturn(response);

        //Then
        LotteryResponse addedTicket = lotteryService.addTicket(lotteryReq);
        assertThat(addedTicket).isEqualTo(response);
        verify(lotteryService).addTicket(lotteryReq);
    }

    @Test
    @DisplayName("Should return tickets list ['123456', '543210']")
    void getTickets() {
        //Given
        List<String> tickets = List.of("123456", "543210");

        //When
        when(lotteryService.getTickets()).thenReturn(new LotteryResponse(tickets));

        //Then
        LotteryResponse result = lotteryService.getTickets();
        assertThat(result.getTickets()).isEqualTo(tickets);
        verify(lotteryService).getTickets();
    }

    @Test
    @DisplayName("Should return user ticket ID")
    void purchaseTicket() {
        //Given
        String userId = "0123456789";
        String ticketId = "123456";
        PurchaseTicketResponse response = new PurchaseTicketResponse("1");
        Lottery lottery = new Lottery();
        lottery.setTicket(ticketId);
        lottery.setPrice(100.0);
        lottery.setAmount(1);

        //When
        when(lotteryRepository.findById(any())).thenReturn(Optional.of(lottery));
        when(lotteryService.purchaseTicket(userId, ticketId)).thenReturn(response);

        //Then
        PurchaseTicketResponse result = lotteryService.purchaseTicket(userId, ticketId);
        assertThat(result).isEqualTo(response);
        verify(lotteryService).purchaseTicket(userId, ticketId);
    }

    @Test
    @DisplayName("Should return ticket ID that user sell back")
    void sellBackTicket() {
        //Given
        String userId = "0123456789";
        String ticketId = "123456";
        LotteryResponse response = new LotteryResponse("123456");

        //When
        when(userTicketRepository.deleteByUserIdAndTicketId(any(), any())).thenReturn(1);
        when(lotteryService.sellBackTicket(userId, ticketId)).thenReturn(response);

        //Then
        LotteryResponse result = lotteryService.sellBackTicket(userId, ticketId);
        assertThat(result).isEqualTo(response);
        verify(lotteryService).sellBackTicket(userId, ticketId);
    }

    @Test
    @DisplayName("Should return purchase list")
    void getPurchaseList() {
        //Given
        List<String> tickets = List.of("789412", "742315");
        String userId = "0123456789";
        PurchaseListResponse response = new PurchaseListResponse(tickets, 2, 100);

        Lottery lotteryOne = new Lottery();
        lotteryOne.setTicket("789412");
        lotteryOne.setPrice(100.0);
        lotteryOne.setAmount(1);

        Lottery lotteryTwo = new Lottery();
        lotteryTwo.setTicket("789412");
        lotteryTwo.setPrice(100.0);
        lotteryTwo.setAmount(1);

        UserTicket userTicketOne = new UserTicket();
        userTicketOne.setUserId(userId);
        userTicketOne.setLottery(lotteryOne);

        UserTicket userTicketTwo = new UserTicket();
        userTicketTwo.setUserId(userId);
        userTicketTwo.setLottery(lotteryTwo);

        //When
        when(userTicketRepository.findAllByUserId(any())).thenReturn(List.of(userTicketOne, userTicketTwo));
        when(lotteryService.getPurchaseList(userId)).thenReturn(response);

        //Then
        PurchaseListResponse result = lotteryService.getPurchaseList(userId);
        assertThat(result).isEqualTo(response);
    }
}