package com.example.TicketingSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TicketingSystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}

