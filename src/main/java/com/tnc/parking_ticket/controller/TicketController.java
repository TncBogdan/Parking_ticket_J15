package com.tnc.parking_ticket.controller;

import com.tnc.parking_ticket.entity.Ticket;
import com.tnc.parking_ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/getTicket", method = RequestMethod.GET)
    public Ticket getOne() {
        return ticketService.getTicket();
    }
}
