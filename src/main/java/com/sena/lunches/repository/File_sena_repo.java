package com.sena.lunches.repository;

import com.sena.lunches.entities.File_sena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface File_sena_repo extends JpaRepository<File_sena, Integer>{

}
