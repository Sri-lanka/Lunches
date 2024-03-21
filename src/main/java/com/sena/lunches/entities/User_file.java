package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User_file {
    @Id
    private int id_user_file;
    private int id_file;
    private int id_user ;
    private int asset;

    public int getId_user_file() {
        return id_user_file;
    }

    public void setId_user_file(int id_user_file) {
        this.id_user_file = id_user_file;
    }

    public int getId_file() {
        return id_file;
    }

    public void setId_file(int id_file) {
        this.id_file = id_file;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getAsset() {
        return asset;
    }

    public void setAsset(int asset) {
        this.asset = asset;
    }
}
