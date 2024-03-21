package com.sena.lunches.repository;

import com.sena.lunches.entities.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Authorization_sena_repo extends JpaRepository<Authorization,Integer> {
}
