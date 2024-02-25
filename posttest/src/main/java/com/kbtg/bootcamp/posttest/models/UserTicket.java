package com.kbtg.bootcamp.posttest.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_ticket")
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private String userId;

    @JoinColumn(name ="ticket", referencedColumnName = "ticket")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lottery lottery;

}
