package com.sena.lunches.repository;

import com.sena.lunches.entities.Archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Archive_repo extends JpaRepository<Archive, Integer> {

}
