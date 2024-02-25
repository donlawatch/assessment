package com.kbtg.bootcamp.posttest.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class LotteryRepositoryTest {
    @Mock
    private LotteryRepository lotteryRepository;

    @Test
    @DisplayName("When perform find all tickets, then return 2 tickets list")
    void findAllTicketsShouldReturnTickets() {
        //Given
        List<String> ticketsMock = List.of("123456", "654321");

        //When
        when(lotteryRepository.findAllTickets()).thenReturn(ticketsMock);
        List<String> tickets = lotteryRepository.findAllTickets();

        //Then
        assertEquals(2, tickets.size());
        verify(this.lotteryRepository).findAllTickets();
    }
}