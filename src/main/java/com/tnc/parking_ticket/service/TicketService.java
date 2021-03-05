package com.tnc.parking_ticket.service;

import com.tnc.parking_ticket.repository.TicketRepository;
import com.tnc.parking_ticket.repository.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
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

    public Ticket setNewTicket(Long id) {
        var ticketToPay = ticketRepository.getOne(id);
        ticketToPay.setExitDate(LocalDateTime.now());
        return ticketToPay;
    }

    public void calculateTicketPayment(Long id) {

        setNewTicket(id);

        var calculateParkingTime = Duration.between(setNewTicket(id).getEnterDate(), setNewTicket(id).getExitDate()).toMinutes();

        setAmount(setNewTicket(id), calculateParkingTime);

        validateTicket(setNewTicket(id));

        ticketRepository.save(setNewTicket(id));

        System.out.println(setNewTicket(id));

        System.out.println("Time is: " + calculateParkingTime + " minutes.");
        System.out.println("Your payment is " + setNewTicket(id).getPayAmount() + " lei.");
        payTicket(setNewTicket(id), id);
    }

    public void payTicket(Ticket ticketToPay, Long id) {
        if (ticketToPay.isPaid()) {
            System.out.println("Ticket " + id + " exit from parking.");
        }
    }

    private String generateTicketCode() {
        return "T" + (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000);
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

    public void validateTicket(Ticket ticketValidator) {
        try {
            if (ticketValidator.isPaid()) {
                throw new IllegalArgumentException("Invalid ticket!");
            } else {
                ticketValidator.setPaid(true);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("The ticket was paid.");
        }
    }
}
