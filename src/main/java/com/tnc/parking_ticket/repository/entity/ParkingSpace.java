package com.tnc.parking_ticket.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "parking_space")
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isFree;
    private Integer number;


}
