package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Benefit {
    @Id
    private int id_benefit;
    private String nom_benefit;
    private String description_benefit;
    private LocalDate date_start;
    private  LocalDate date_end;

    public int getId_benefit() {
        return id_benefit;
    }

    public void setId_benefit(int id_benefit) {
        this.id_benefit = id_benefit;
    }

    public String getNom_benefit() {
        return nom_benefit;
    }

    public void setNom_benefit(String nom_benefit) {
        this.nom_benefit = nom_benefit;
    }

    public String getDescription_benefit() {
        return description_benefit;
    }

    public void setDescription_benefit(String description_benefit) {
        this.description_benefit = description_benefit;
    }

    public LocalDate getDate_start() {
        return date_start;
    }

    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }
}
