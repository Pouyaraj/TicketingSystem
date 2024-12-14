package com.example.TicketingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TicketingSystem.entity.UserEntity;
import com.example.TicketingSystem.service.AccountService;

@RestController
public class accountController {
    private final AccountService accountService;

    @Autowired
    public accountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserEntity user){
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || 
        user.getPassword() == null || user.getPassword().length() < 4) {
        return ResponseEntity.status(400).body("Error.");
    }

    try {
        UserEntity savedUser = accountService.register(user);
        return ResponseEntity.ok(savedUser);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(409).body("The username has been registered.");
    }
    }
}
