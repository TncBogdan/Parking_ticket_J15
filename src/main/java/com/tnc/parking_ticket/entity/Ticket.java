package com.tnc.parking_ticket.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Integer payAmount;
    private LocalDateTime enterDate;
    private LocalDateTime exitDate;
    private boolean isPaid;
}
