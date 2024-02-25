package com.kbtg.bootcamp.posttest.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lottery")
public class Lottery {
    @Id
    private String ticket;
    private Double price;
    private int amount;

}
