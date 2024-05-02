package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Entity
public class Assistance {
    @Id
    private int id_assistance;
    private int id_authorization;

   private LocalDateTime date_time;

    public int getId_assistance() {
        return id_assistance;
    }

    public void setId_assistance(int id_assistance) {
        this.id_assistance = id_assistance;
    }

    public int getId_authorization() {
        return id_authorization;
    }

    public void setId_authorization(int id_authorization) {
        this.id_authorization = id_authorization;
    }


    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }
}
