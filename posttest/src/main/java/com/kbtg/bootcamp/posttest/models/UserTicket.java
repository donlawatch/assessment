package com.kbtg.bootcamp.posttest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    private String userID;


}
