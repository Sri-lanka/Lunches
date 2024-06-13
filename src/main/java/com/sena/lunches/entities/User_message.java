package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User_message {
    @Id
    private  int idUserMessage;
    private int id_user;
    private int id_message;
    private int recipient;

    public int getIdUserMessage() {
        return idUserMessage;
    }

    public void setIdUserMessage(int idUserMessage) {
        this.idUserMessage = idUserMessage;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }
}
