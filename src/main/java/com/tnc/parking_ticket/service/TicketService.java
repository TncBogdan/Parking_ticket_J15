package com.tnc.parking_ticket.service;

import com.tnc.parking_ticket.entity.Ticket;
import com.tnc.parking_ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    Ticket ticket = new Ticket();

    public Ticket createTicket() {
        var ticket = new Ticket();
        ticket.setEnterDate(LocalDateTime.now());
        ticket.setCode(generateTicketCode());

        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket findById(Long id) {
        Optional<Ticket> findOne = ticketRepository.findById(id);
        return findOne.orElseThrow();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public void  payTicket(Long id){

        ticketRepository.getOne(ticket.getId());

        ticketRepository.deleteById(id);

        System.out.println("Ticket " + id + "exit from parking.");
    }


    private String generateTicketCode() {
        return "T" + (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000);
    }

}
