package com.sena.lunches.repository;

import com.sena.lunches.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Message_repo extends  JpaRepository<Message,Integer>{
}
