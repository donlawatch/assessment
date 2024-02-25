package com.kbtg.bootcamp.posttest.repositories;

import com.kbtg.bootcamp.posttest.models.UserTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTicketRepositoryTest {
    @Mock
    UserTicketRepository userTicketRepository;
    @Test
    @DisplayName("When perform delete should return a number of deleted rows")
    void deleteByUserIdAndTicketId() {
        //Given
        String userId = "0123456789";
        String ticketId = "123456";

        //When
        when(userTicketRepository.deleteByUserIdAndTicketId(userId, ticketId)).thenReturn(1);
        int deletedRows = userTicketRepository.deleteByUserIdAndTicketId(userId, ticketId);

        //Then
        int expectedDeletedRow = 1;
        assertEquals(expectedDeletedRow, deletedRows);
        verify(userTicketRepository).deleteByUserIdAndTicketId(userId, ticketId);
    }

    @Test
    @DisplayName("Should return purchase list of given user id")
    void findAllByUserId() {
        //Given
        String userId = "0123456660";
        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(userId);
        List<UserTicket> purchaseListMock = List.of(userTicket);

        //When
        when(userTicketRepository.findAllByUserId(userId)).thenReturn(purchaseListMock);
        List<UserTicket> purchaseList = userTicketRepository.findAllByUserId(userId);

        //Then
        List<UserTicket> expectedPurchaseList = List.of(userTicket);
        assertEquals(expectedPurchaseList, purchaseList);
        verify(userTicketRepository).findAllByUserId(userId);

    }
}