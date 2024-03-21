package com.sena.lunches.repository;

import com.sena.lunches.entities.Program;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Program_sena_repo extends JpaRepository<Program,Integer>{

}
