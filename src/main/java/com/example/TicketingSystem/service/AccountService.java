package com.example.TicketingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TicketingSystem.entity.UserEntity;
import com.example.TicketingSystem.repository.TicketRepository;
import com.example.TicketingSystem.repository.UserRepository;

@Service
public class AccountService {

    private final TicketRepository ticketRepository;

    private final UserRepository usertRepository;

    @Autowired
    public AccountService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.usertRepository = userRepository;
    }

    public UserEntity register(UserEntity user) {
        // Check if the user with the given username already exists
        UserEntity existingAccount = usertRepository.findByUsername(user.getUsername());

        // If the user exists, throw an exception to prevent registration
        if (existingAccount != null) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }

        // Save the new user to the database if it doesn't exist
        return usertRepository.save(user);
    }

    public UserEntity login(UserEntity user) {
        // Find user by username
        Optional<UserEntity> existingUser = Optional.ofNullable(usertRepository.findByUsername(user.getUsername()));

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return existingUser.get();
        }
        throw new IllegalArgumentException("Invalid username or password!");
    }
}
