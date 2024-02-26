package com.kbtg.bootcamp.posttest.services;

import com.kbtg.bootcamp.posttest.dtos.requests.LotteryRequest;
import com.kbtg.bootcamp.posttest.dtos.responses.LotteryResponse;
import com.kbtg.bootcamp.posttest.dtos.responses.PurchaseListResponse;
import com.kbtg.bootcamp.posttest.dtos.responses.PurchaseTicketResponse;
import com.kbtg.bootcamp.posttest.exceptions.NotFoundException;
import com.kbtg.bootcamp.posttest.models.Lottery;
import com.kbtg.bootcamp.posttest.models.UserTicket;
import com.kbtg.bootcamp.posttest.repositories.LotteryRepository;

import com.kbtg.bootcamp.posttest.repositories.UserTicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;
    private final UserTicketRepository userTicketRepository;

    public LotteryService(LotteryRepository lotteryRepository, UserTicketRepository userTicketRepository) {
        this.lotteryRepository = lotteryRepository;
        this.userTicketRepository = userTicketRepository;
    }

    public LotteryResponse addTicket(LotteryRequest lotteryRequest) {
        Optional<Lottery> optionalLottery = lotteryRepository.findById(lotteryRequest.getTicket());


        //If the ticket already exists, then update the existing one.
        if(optionalLottery.isPresent()) {
           Lottery lottery = optionalLottery.get();
           int newAmount = lottery.getAmount() + lotteryRequest.getAmount();

           lottery.setPrice(lotteryRequest.getPrice());
           lottery.setAmount(newAmount);
           lotteryRepository.save(lottery);

           return new LotteryResponse(lottery.getTicket());
        }

        //Add a new ticket
        Lottery newLottery = new Lottery();

        newLottery.setTicket(lotteryRequest.getTicket());
        newLottery.setPrice(lotteryRequest.getPrice());
        newLottery.setAmount(lotteryRequest.getAmount());

        lotteryRepository.save(newLottery);

        return new LotteryResponse(newLottery.getTicket());
    }

    public LotteryResponse getTickets() {
        List<String> tickets = lotteryRepository.findAllTickets();

        return new LotteryResponse(tickets);
    }

    public PurchaseTicketResponse purchaseTicket(String userId, String ticket) {
        Optional<Lottery> optionalLottery = lotteryRepository.findById(ticket);

        if (optionalLottery.isEmpty()) {
            throw  new NotFoundException("Ticket not found!");
        }

        Lottery lottery = optionalLottery.get();
        UserTicket userTicket = new UserTicket();

        userTicket.setUserId(userId);
        userTicket.setLottery(lottery);

        userTicketRepository.save(userTicket);
        String id = String.valueOf(userTicket.getId());
        return new PurchaseTicketResponse(id);
    }

    public LotteryResponse sellBackTicket(String userId, String ticket) {
        int deletedRows = userTicketRepository.deleteByUserIdAndTicketId(userId, ticket);

        if (deletedRows == 0) {
            throw new NotFoundException(String.format("user ID: %s with ticket ID: %s is not existed", userId, ticket));
        }

        return new LotteryResponse(ticket);
    }

    public PurchaseListResponse getPurchaseList(String userId) {
        List<UserTicket> purchaseList = userTicketRepository.findAllByUserId(userId);

        if (purchaseList.isEmpty()) {
            throw new NotFoundException("Purchase list not found.");
        }

        double price = 0;
        int amount = purchaseList.size();
        List<String> tickets = new ArrayList<>();

        for (UserTicket userTicket : purchaseList) {
            Lottery lottery = userTicket.getLottery();

            tickets.add(lottery.getTicket());
            price += lottery.getPrice();
        }

        return new PurchaseListResponse(tickets, amount, price);
    }
}
