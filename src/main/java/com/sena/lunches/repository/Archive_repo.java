package com.sena.lunches.repository;

import com.sena.lunches.entities.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//this code declares a repository interface (Archive_repo) for managing instances of the Archive entity.
//This interface inherits a set of methods for basic CRUD (Create, Read, Update, Delete) operations from JpaRepository.
//Additionally, it inherits methods for pagination and sorting from PagingAndSortingRepository@Repository
@Repository
public interface Archive_repo extends JpaRepository<Archive, Integer> {

}
