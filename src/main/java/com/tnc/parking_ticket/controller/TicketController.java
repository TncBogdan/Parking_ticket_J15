package com.tnc.parking_ticket.controller;

import com.tnc.parking_ticket.entity.Ticket;
import com.tnc.parking_ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
    public Ticket createOneTicket() {
        return ticketService.createTicket();
    }

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
    public Optional<Ticket> getById(@PathVariable Long id) {
        var getTicket = ticketService.findById(id);
        return getTicket;
    }

    @RequestMapping(value = "/ticket/getAll")
    public List<Ticket> getAll() {
        return ticketService.findAll();
    }

    @RequestMapping(value = "/pay/{id}")
    public void pay() {
        ticketService.payTicket(createOneTicket().getId());
    }
}
