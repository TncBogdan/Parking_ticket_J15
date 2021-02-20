package com.tnc.parking_ticket.service;

import com.tnc.parking_ticket.entity.Ticket;
import com.tnc.parking_ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public Ticket getTicket() {
        var ticket = new Ticket();
        ticket.setEnterDate(LocalDateTime.now());
        ticket.setCode(generateTicketCode());

        ticketRepository.save(ticket);
        return ticket;
    }


    private String generateTicketCode() {
        return "T" + (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000);
    }
}
