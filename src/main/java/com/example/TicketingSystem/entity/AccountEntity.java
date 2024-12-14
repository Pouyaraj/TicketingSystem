package com.example.TicketingSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_account")
public class AccountEntity {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="username", nullable=false, unique=true)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    public AccountEntity(){
        
    }

    public AccountEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getID(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getfistName(){
        return firstName;
    }

    public void setfirstName(String firstName){
        this.firstName = firstName;
    }

    public String getlastName(){
        return lastName;
    }

    public void setlastNmae(String lastName){
        this.lastName = lastName;
    }
}
