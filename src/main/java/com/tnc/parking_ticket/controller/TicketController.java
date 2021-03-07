package com.tnc.parking_ticket.controller;

import com.tnc.parking_ticket.repository.entity.Ticket;
import com.tnc.parking_ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/ticket")
    public Ticket createOneTicket() {
        return ticketService.createTicket();
    }

    @GetMapping ("/ticket/{id}")
    public Optional<Ticket> getById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @GetMapping ("/ticket/getAll")
    public List<Ticket> getAll() {
        return ticketService.findAll();
    }

    @GetMapping ("/ticket/pay/{id}")
    public void pay(@PathVariable Long id) {
        ticketService.calculateTicketPayment(id);
    }
}
