package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Excuse {

    @Id
    private int id_excuse;

    private int id_user;

    private int id_assistance;

    private String description_excuse;


    public int getId_excuse() {
        return id_excuse;
    }

    public void setId_excuse(int id_excuse) {
        this.id_excuse = id_excuse;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_assistance() {
        return id_assistance;
    }

    public void setId_assistance(int id_assistance) {
        this.id_assistance = id_assistance;
    }

    public String getDescription_excuse() {
        return description_excuse;
    }

    public void setDescription_excuse(String description_excuse) {
        this.description_excuse = description_excuse;
    }
}
