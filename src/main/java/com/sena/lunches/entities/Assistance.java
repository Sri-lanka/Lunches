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
   private  int id_user;
   private LocalDateTime times;

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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public LocalDateTime getTimes() {
        return times;
    }

    public void setTimes(LocalDateTime times) {
        this.times = times;
    }
}
