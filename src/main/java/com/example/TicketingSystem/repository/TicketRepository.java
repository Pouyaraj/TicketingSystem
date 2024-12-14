package com.example.TicketingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TicketingSystem.entity.TicketEntity;
import com.example.TicketingSystem.entity.UserEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    List<TicketEntity> findByUser(UserEntity user);
}
