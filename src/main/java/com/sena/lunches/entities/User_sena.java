package com.sena.lunches.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
*/
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User_sena {
    /*implements UserDetails*/
    @Id
    private int id_user;
    private int document;
    private int type_document;
    private String nameUser;
    private String lastName;
    private String email;
    private long telephone;
    private String keyword;
    private int state;
    private int roles;

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public void setType_document(int type_document) {
        this.type_document = type_document;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setState(int state) {
        this.state = state;
    }



    public int getId_user() {
        return id_user;
    }

    public int getDocument() {
        return document;
    }

    public int getType_document() {
        return type_document;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public long getTelephone() {
        return telephone;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getState() {
        return state;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }
/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public String getPassword() {
        return keyword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }*/
}
