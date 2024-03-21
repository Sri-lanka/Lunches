package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Message {
    @Id
    private  int id_message;
    private int id_user;
    private String description_message;
    private LocalDate date_send;

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDescription_message() {
        return description_message;
    }

    public void setDescription_message(String description_message) {
        this.description_message = description_message;
    }

    public LocalDate getDate_send() {
        return date_send;
    }

    public void setDate_send(LocalDate date_send) {
        this.date_send = date_send;
    }
}
