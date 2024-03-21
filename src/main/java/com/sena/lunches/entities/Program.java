package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Program {
    @Id
    private int id_program;
    private String name_program;

    public int getId_program() {
        return id_program;
    }

    public void setId_program(int id_program) {
        this.id_program = id_program;
    }

    public String getName_program() {
        return name_program;
    }

    public void setName_program(String name_program) {
        this.name_program = name_program;
    }
}
