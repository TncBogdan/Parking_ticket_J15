package com.tnc.parking_ticket.service;

import com.tnc.parking_ticket.repository.TicketRepository;
import com.tnc.parking_ticket.repository.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;


    public Ticket createTicket() {
        var ticket = new Ticket();
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

        setNewTicket(id);

        var calculateParkingTime = Duration.between(setNewTicket(id).getEnterDate(), setNewTicket(id).getExitDate()).toMinutes();

        setAmount(setNewTicket(id), calculateParkingTime);

        try {
            if (setNewTicket(id).isPaid()) {
                throw new IllegalArgumentException();
            } else {
                setNewTicket(id).setPaid(true);
                System.out.println(setNewTicket(id));
                System.out.println("Time is: " + calculateParkingTime + " minutes.");
                System.out.println("Your payment is " + setNewTicket(id).getPayAmount() + " lei.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("The ticket was paid.");
        }

        ticketRepository.save(setNewTicket(id));
    }

    private String generateTicketCode() {
        return "T" + (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000);
    }

    public Ticket setNewTicket(Long id) {
        var ticketToPay = ticketRepository.getOne(id);
        ticketToPay.setExitDate(LocalDateTime.now());
        return ticketToPay;
    }

    public void setAmount(Ticket ticketId, long timeResult) {
        if (timeResult <= 60) {
            ticketId.setPayAmount(2);
        } else if (timeResult <= 120) {
            ticketId.setPayAmount(4);
        } else if (timeResult <= 180) {
            ticketId.setPayAmount(6);
        } else if (timeResult <= 240) {
            ticketId.setPayAmount(8);
        } else if (timeResult <= 300) {
            ticketId.setPayAmount(10);
        } else {
            ticketId.setPayAmount(15);
        }
    }
}
