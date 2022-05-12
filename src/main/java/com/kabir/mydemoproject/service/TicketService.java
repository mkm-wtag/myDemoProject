package com.kabir.mydemoproject.service;

import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.models.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets(Long userId);

    Ticket getTicket(Long userId, Long ticketId);

    Ticket create(Long userId, Ticket ticket);

    MyResponse delete(Long userId, Long ticketId);
}
