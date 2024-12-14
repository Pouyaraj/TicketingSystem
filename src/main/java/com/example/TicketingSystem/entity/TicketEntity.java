package com.example.TicketingSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ticket")
public class TicketEntity {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer id;

    @Column(name="amount", nullable=false)
    private Integer amount;

    @Column(name="description", nullable=false)
    private String description;

    @Column(name="status", nullable=false)
    private String status = "pending";

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private AccountEntity user;

    public TicketEntity(Integer amount, String description, String status, AccountEntity user){
        this.amount = amount;
        this.description = description;
        this.status = "pending";
        this.user = user;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getAmount(){
        return amount;
    }

    public void setAmount(Integer amount){
        this.amount = amount;
    }

    public String  getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
