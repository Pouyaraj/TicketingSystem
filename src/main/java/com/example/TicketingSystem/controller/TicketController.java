package com.example.TicketingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TicketingSystem.entity.TicketEntity;
import com.example.TicketingSystem.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> createTicket(@RequestBody TicketEntity ticket) {
        // Validate input fields
        if (ticket.getDescription() == null || ticket.getDescription().trim().isEmpty() ||
            ticket.getAmount() == null) {
            return ResponseEntity.status(400).body("Error: All fields are required.");
        }

        try {
            // Call the service to submit the ticket
            TicketEntity savedTicket = ticketService.submit(ticket);
            return ResponseEntity.ok(savedTicket);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body("Error: " + e.getMessage());
        }
    }
}