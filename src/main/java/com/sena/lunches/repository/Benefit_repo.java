package com.sena.lunches.repository;

import com.sena.lunches.entities.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Benefit_repo extends JpaRepository<Benefit,Integer> {
}
