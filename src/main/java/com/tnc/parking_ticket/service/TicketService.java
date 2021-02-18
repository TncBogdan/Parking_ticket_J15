package com.tnc.parking_ticket.service;

import com.tnc.parking_ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    TicketRepository ticketRepository;

    private String generateTicketCode(){
        return "T" + (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000);
    }
}
