package com.tnc.parking_ticket.controller;

import com.tnc.parking_ticket.entity.Ticket;
import com.tnc.parking_ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ticket getOne() {
        return ticketService.getTicket();
    }

    @RequestMapping(value = "/getAll")
    public List<Ticket>getAll(){
        return ticketService.findAll();
    }

    @RequestMapping(value = "/pay/{id}")
    public void pay(){
        ticketService.payTicket(getOne().getId());
    }
}
