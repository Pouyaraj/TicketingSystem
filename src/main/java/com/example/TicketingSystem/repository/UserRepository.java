package com.example.TicketingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TicketingSystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
