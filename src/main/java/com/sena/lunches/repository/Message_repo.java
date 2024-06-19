package com.sena.lunches.repository;

import com.sena.lunches.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Message_repo extends  JpaRepository<Message,Integer>{

    List<Message> findByTypeMessage(int typeMessage);
}
