package com.sena.lunches.repository;

import com.sena.lunches.entities.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Assistance_repo extends JpaRepository<Assistance,Integer>{
}
