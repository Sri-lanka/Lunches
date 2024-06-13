package com.sena.lunches.repository;

import com.sena.lunches.entities.User_message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageRepo extends  JpaRepository<User_message,Integer>{
}
