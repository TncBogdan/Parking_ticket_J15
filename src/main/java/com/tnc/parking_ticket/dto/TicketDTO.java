package com.tnc.parking_ticket.dto;

import java.time.LocalDateTime;

public record TicketDTO(Long id, String code, LocalDateTime enterDate) {
}
