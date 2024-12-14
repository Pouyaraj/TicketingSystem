package com.example.TicketingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TicketingSystem.entity.AccountEntity;
import com.example.TicketingSystem.entity.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    List<TicketEntity> findByUser(AccountEntity user);
}
