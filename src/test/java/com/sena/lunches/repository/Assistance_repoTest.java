package com.sena.lunches.repository;

import com.sena.lunches.entities.Assistance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Rollback(false)

public class Assistance_repoTest {

    @Autowired
    private Assistance_repo assistance_repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveAssistance(){

        // Given
        Assistance assistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        // When
        Assistance savedAssistence = entityManager.persist(assistance);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedAssistence.getId_assistance()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the benefit can be retrieved from the repository

        Optional<Assistance> retrievedAssistence = assistance_repo.findById(savedAssistence.getId_assistance());
        assertThat(retrievedAssistence).isPresent(); // Check if benefit is found
        assertThat(retrievedAssistence.get().getId_authorization()).isEqualTo(20); // Verify attribute value


    }

    @Test
    public void testGetAssistence(){
        // Given
        Assistance assistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        Assistance assistance2 = Assistance.builder()
                .id_assistance(17)
                .id_authorization(21)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        assistance_repo.save(assistance);
        assistance_repo.save(assistance2);

        List<Assistance> assistanceList = assistance_repo.findAll();

        assertThat(assistanceList).isNotNull();
        assertThat(assistanceList.size()).isEqualTo(2);

    }

    @Test
    public void testFindAssistence(){
        // Given
        Assistance assistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();
        // When
        Assistance savedAssistence = assistance_repo.save(assistance);


        // Then
        assertThat(savedAssistence.getId_assistance()).isNotNull(); // Verifica que el ID se haya asignado correctamente

        Optional<Assistance> retrievedAssistenceOptional = assistance_repo.findById(savedAssistence.getId_assistance());

        assertThat(retrievedAssistenceOptional).isPresent();
        Assistance retrievedAssistence = retrievedAssistenceOptional.get();
        assertThat(retrievedAssistence.getId_authorization()).isEqualTo(20); // Verifica el nombre del beneficio

    }

    @Test
    public void testUpdateAssistence(){
        // Given
        Assistance assistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        Assistance savedAssistence = assistance_repo.save(assistance);

        // Retrieve the saved benefit from the repository
        Optional<Assistance> optionalAssistance = assistance_repo.findById(savedAssistence.getId_assistance());
        assertThat(optionalAssistance).isPresent(); // Verify that the benefit is found

        Assistance retrievedAssistence = optionalAssistance.get();
        retrievedAssistence.setId_authorization(21);


        Assistance updatedAssistence = assistance_repo.save(retrievedAssistence);

        assertThat(updatedAssistence.getId_assistance()).isEqualTo(savedAssistence.getId_assistance()); // Verify ID remains the same
        assertThat(updatedAssistence.getId_authorization()).isEqualTo(21); // Verify updated name

    }

    @Test
    public void testDeleteAssistence(){

        // Given
        Assistance assistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        assistance_repo.save(assistance);
        assistance_repo.deleteById(assistance.getId_assistance());
        Optional<Assistance> assistenceReturn = assistance_repo.findById(assistance.getId_assistance());

        assertThat(assistenceReturn).isEmpty();
    }

}