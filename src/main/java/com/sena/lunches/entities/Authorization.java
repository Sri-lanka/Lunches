package com.sena.lunches.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Authorizations")
public class Authorization {

    @Id
    private int id_authorization;

    private int id_user;

    private int id_user_autho;

    private  String description_excuse;
    private LocalDate date_apli;

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

    public int getId_user_autho() {
        return id_user_autho;
    }

    public void setId_user_autho(int id_user_autho) {
        this.id_user_autho = id_user_autho;
    }


    public String getDescription_excuse() {
        return description_excuse;
    }

    public void setDescription_excuse(String description_excuse) {
        this.description_excuse = description_excuse;
    }

    public LocalDate getDate_apli() {
        return date_apli;
    }

    public void setDate_apli(LocalDate date_apli) {
        this.date_apli = date_apli;
    }
}
