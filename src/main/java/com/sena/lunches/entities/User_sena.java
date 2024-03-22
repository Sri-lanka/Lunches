package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class User_sena {
    @Id
    private int id_user;
    private int document;
    private int type_document;
    private int rol;
    private String name_1;
    private String name_2;
    private String last_name_1;
    private String last_name_2;
    private int age;
    private String Email;
    private int telephone;
    private String keyword;
    private int state;
    private int id_benefit;


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public int getType_document() {
        return type_document;
    }

    public void setType_document(int type_document) {
        this.type_document = type_document;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getName_1() {
        return name_1;
    }

    public void setName_1(String name_1) {
        this.name_1 = name_1;
    }

    public String getName_2() {
        return name_2;
    }

    public void setName_2(String name_2) {
        this.name_2 = name_2;
    }

    public String getLast_name_1() {
        return last_name_1;
    }

    public void setLast_name_1(String last_name_1) {
        this.last_name_1 = last_name_1;
    }

    public String getLast_name_2() {
        return last_name_2;
    }

    public void setLast_name_2(String last_name_2) {
        this.last_name_2 = last_name_2;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId_benefit() {
        return id_benefit;
    }

    public void setId_benefit(int id_benefit) {
        this.id_benefit = id_benefit;
    }


}
