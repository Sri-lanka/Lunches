package com.sena.lunches.repository;

import com.sena.lunches.entities.User_sena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface User_sena_repo extends JpaRepository<User_sena,Integer> {

    Optional<User_sena> findByEmail(String Email);
    User_sena findByDocumentAndKeyword(int document, String keyword);
/*
    User_sena findByIdentification(int document);
*/
}
