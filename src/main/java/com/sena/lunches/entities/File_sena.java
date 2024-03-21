package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class File_sena {
    @Id
    private int id_file;

    private int n_file;

    private int id_program;

    public int getId_file() {
        return id_file;
    }

    public void setId_file(int id_file) {
        this.id_file = id_file;
    }

    public int getN_file() {
        return n_file;
    }

    public void setN_file(int n_file) {
        this.n_file = n_file;
    }

    public int getId_program() {
        return id_program;
    }

    public void setId_program(int id_program) {
        this.id_program = id_program;
    }
}
