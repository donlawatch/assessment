package com.kbtg.bootcamp.posttest.repositories;

import com.kbtg.bootcamp.posttest.models.UserTicket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    @Transactional
    @Modifying
    @Query("delete from UserTicket ut where ut.userId=:userId and ut.lottery.ticket=:ticketId")
    int deleteByUserIdAndTicketId(@Param("userId") String userId, @Param("ticketId") String ticketId);

    List<UserTicket> findAllByUserId(String userId);
}
