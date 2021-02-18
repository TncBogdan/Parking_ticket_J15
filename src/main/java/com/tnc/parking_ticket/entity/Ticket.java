package com.tnc.parking_ticket.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

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

    @Range(min = 0, message = "Invalid payed amount")
    private Integer paydAmount;

    private LocalDateTime enterDate;
    private LocalDateTime exitDate;
    private LocalDateTime PayDate;
}
