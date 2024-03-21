package com.sena.lunches.repository;

import com.sena.lunches.entities.Excuse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Excuse_sena_repo extends JpaRepository<Excuse,Integer> {
}
