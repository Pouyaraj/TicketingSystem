package com.example.TicketingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TicketingSystem.entity.AccountEntity;
import com.example.TicketingSystem.service.AccountService;

@RestController
public class accountController {
    private final AccountService accountService;

    @Autowired
    public accountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountEntity user){
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || 
        user.getPassword() == null || user.getPassword().length() < 4) {
        return ResponseEntity.status(400).body("Error.");
    }

    try {
        AccountEntity savedUser = accountService.register(user);
        return ResponseEntity.ok(savedUser);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(409).body("The username has been registered.");
    }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountEntity user){
        if(user.getUsername() == null || user.getPassword() == null){
            return ResponseEntity.status(401).body("Unauthorized");
        }

        try {
            AccountEntity verifiedAccout = accountService.login(user);
            return ResponseEntity.ok(verifiedAccout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("Error");
        }
    }
}
