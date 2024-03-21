package com.sena.lunches.repository;

import com.sena.lunches.entities.User_sena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface User_sena_repo extends JpaRepository<User_sena,Integer> {


}
