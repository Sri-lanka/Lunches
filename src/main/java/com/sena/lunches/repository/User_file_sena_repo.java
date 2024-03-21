package com.sena.lunches.repository;


import com.sena.lunches.entities.User_file;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_file_sena_repo extends JpaRepository<User_file,Integer> {
}
