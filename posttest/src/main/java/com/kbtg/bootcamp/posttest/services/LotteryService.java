package com.kbtg.bootcamp.posttest.services;

import com.kbtg.bootcamp.posttest.dtos.requests.LotteryRequest;
import com.kbtg.bootcamp.posttest.dtos.responses.LotteryResponse;
import com.kbtg.bootcamp.posttest.models.Lottery;
import com.kbtg.bootcamp.posttest.repositories.LotteryRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public LotteryResponse addTicket(LotteryRequest lotteryRequest) {
        Optional<Lottery> optionalLottery = lotteryRepository.findByTicket(lotteryRequest.getTicket());
        LotteryResponse response = new LotteryResponse();

        //If the ticket already exists, then update the existing one.
        if(optionalLottery.isPresent()) {
           Lottery lottery = optionalLottery.get();
           int newAmount = lottery.getAmount() + lotteryRequest.getAmount();

           lottery.setPrice(lotteryRequest.getPrice());
           lottery.setAmount(newAmount);
           lotteryRepository.save(lottery);

           response.setTicket(lottery.getTicket());
           return response;
        }

        //Add a new ticket
        Lottery newLottery = new Lottery();

        newLottery.setTicket(lotteryRequest.getTicket());
        newLottery.setPrice(lotteryRequest.getPrice());
        newLottery.setAmount(lotteryRequest.getAmount());

        lotteryRepository.save(newLottery);

        response.setTicket(newLottery.getTicket());
        return response;
    }

    public LotteryResponse getTickets() {
        List<String> tickets = lotteryRepository.findAllTickets();
        LotteryResponse response = new LotteryResponse();

        response.setTickets(tickets);
        return response;
    }
}
