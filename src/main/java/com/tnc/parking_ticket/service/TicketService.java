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
    private TicketRepository ticketRepository;

    Ticket ticket = new Ticket();

    public Ticket createTicket() {
        ticket.setEnterDate(LocalDateTime.now());
        ticket.setCode(generateTicketCode());

        ticketRepository.save(ticket);
        return ticket;
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.getById(id);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public void payTicket(Long id) {
        var ticketId = ticketRepository.getOne(id);
        ticketId.setExitDate(LocalDateTime.now());
        var isPaid = ticket.isPaid();

        var getEnterHour = ticketId.getEnterDate().getMinute();
        var getExitHour = ticketId.getExitDate().getMinute();

        var timeResult = calculateParkingTime(getExitHour, getEnterHour);

        System.out.println("TimeResult is: " + timeResult);

        if (timeResult < 60) {
            ticket.setPayAmount(2);
            System.out.println(" 2 lei");
            isPaid = true;
        } else if (timeResult > 60) {
            ticket.setPayAmount(4);
            System.out.println(" 4 lei");
            isPaid = true;
        }


        System.out.println(ticketId);

        System.out.println("Ticket " + id + " exit from parking.");
    }

    private String generateTicketCode() {
        return "T" + (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000);
    }

    public int calculateParkingTime(int getEnter, int getExit) {
        return getExit - getEnter;

    }

}
