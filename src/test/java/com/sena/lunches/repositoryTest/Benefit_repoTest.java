package com.sena.lunches.repositoryTest;

import com.sena.lunches.entities.Benefit;
import com.sena.lunches.repository.Benefit_repo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class Benefit_repoTest {
    @Autowired
    private Benefit_repo benefit_repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveBenefit() {
        // Given
        Benefit benefit = Benefit.builder()
                .id_benefit(13)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

        // When
        Benefit savedBenefit = entityManager.persist(benefit);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedBenefit.getId_benefit()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the benefit can be retrieved from the repository

        Optional<Benefit> retrievedBenefit = benefit_repo.findById(savedBenefit.getId_benefit());
        assertThat(retrievedBenefit).isPresent(); // Check if benefit is found
        assertThat(retrievedBenefit.get().getNom_benefit()).isEqualTo("transport"); // Verify attribute value
    }


}
