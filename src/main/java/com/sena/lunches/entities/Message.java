package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    private  int id_message;
    private int id_archive;
    private int typeMessage;
    private String description_message;
    private LocalDate date_send;

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }



    public String getDescription_message() {
        return description_message;
    }

    public void setId_archive(int id_archive) {
        this.id_archive = id_archive;
    }

    public int getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(int typeMessage) {
        this.typeMessage = typeMessage;
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

    public Integer getId_archive() {
        return id_archive;
    }

    public void setId_archive(Integer id_archive) {
        this.id_archive = id_archive;
    }
}
