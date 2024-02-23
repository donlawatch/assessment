package com.kbtg.bootcamp.posttest.repositories;

import com.kbtg.bootcamp.posttest.models.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long> {
    Optional<Lottery> findByTicket(String ticket);

    @Query(value = "select l.ticket  from Lottery l")
    List<String> findAllTickets();
}
