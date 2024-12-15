package com.example.TicketingSystem.service;

import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TicketingSystem.entity.AccountEntity;
import com.example.TicketingSystem.entity.TicketEntity;
import com.example.TicketingSystem.repository.AccountRepository;
import com.example.TicketingSystem.repository.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, AccountRepository accountRepository) {
        this.ticketRepository = ticketRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Method to submit a new ticket.
     * @param ticket The ticket data to be submitted.
     * @return The saved ticket.
     */
    public TicketEntity submit(TicketEntity ticket) {
        // Get the account ID from the user object
        Integer userId = ticket.getUser().getId();
        
        // Use the correct method for finding an account by ID
        Optional<AccountEntity> account = accountRepository.findById(userId);
        if (!account.isPresent()) {
            throw new IllegalArgumentException("Posted by user does not exist.");
        }

        // Set default status as "Pending"
        ticket.setStatus("Pending");

        // Save ticket to the database
        return ticketRepository.save(ticket);
    }
}

