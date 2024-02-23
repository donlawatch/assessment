package com.kbtg.bootcamp.posttest.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "lottery")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ticketId;

    @Getter
    @Setter
    @Column(length = 6)
    private String ticket;

    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private int amount;

}
