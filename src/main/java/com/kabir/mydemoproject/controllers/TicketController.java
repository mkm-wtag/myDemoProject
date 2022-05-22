package com.kabir.mydemoproject.controllers;


import com.kabir.mydemoproject.dto.MyResponse;
import com.kabir.mydemoproject.models.Ticket;
import com.kabir.mydemoproject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users/{userId}/tickets")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> bookTicket(@PathVariable("userId") Long id, @Valid @RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.create(id, ticket), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(ticketService.getAllTickets(userId), HttpStatus.OK);
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("userId") Long userId, @PathVariable("ticketId") Long ticketId) {
        return new ResponseEntity<>(ticketService.getTicket(userId, ticketId), HttpStatus.OK);
    }

    @DeleteMapping("{ticketId}")
    public ResponseEntity<MyResponse> returnTicket(@PathVariable("userId") Long userId, @PathVariable("ticketId") Long ticketId) {
        return new ResponseEntity<>(ticketService.delete(userId, ticketId), HttpStatus.OK);
    }

}
