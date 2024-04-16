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
import java.util.List;
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
                .id_benefit(16)
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

    @Test
    public void testGetBenefit(){
        // Given
        Benefit benefit = Benefit.builder()
                .id_benefit(13)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();
        Benefit benefit2 = Benefit.builder()
                .id_benefit(14)
                .nom_benefit("sustenance")
                .description_benefit("give sustenance")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

        benefit_repo.save(benefit);
        benefit_repo.save(benefit);

        List<Benefit> benefitList = benefit_repo.findAll();

        assertThat(benefitList).isNotNull();
        assertThat(benefitList.size()).isEqualTo(5);
    }


    @Test
    public void testFindBenefit() {
        // Given
        Benefit benefit = Benefit.builder()
                .id_benefit(15)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

        // When
        Benefit savedBenefit = benefit_repo.save(benefit);

        // Then
        assertThat(savedBenefit.getId_benefit()).isNotNull(); // Verifica que el ID se haya asignado correctamente


        Optional<Benefit> retrievedBenefitOptional = benefit_repo.findById(savedBenefit.getId_benefit());


        assertThat(retrievedBenefitOptional).isPresent();
        Benefit retrievedBenefit = retrievedBenefitOptional.get();
        assertThat(retrievedBenefit.getNom_benefit()).isEqualTo("transport"); // Verifica el nombre del beneficio

        // Causes an intentional failure (for example, checking for an incorrect attribute)

        //assertThat(retrievedBenefit.getDescription_benefit()).isEqualTo("incorrect description");


    }

    @Test
    public void testUpdateBenefit() {
        // Given
        Benefit benefit = Benefit.builder()
                .id_benefit(15)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

        Benefit savedBenefit = benefit_repo.save(benefit);

        // Retrieve the saved benefit from the repository
        Optional<Benefit> optionalBenefit = benefit_repo.findById(savedBenefit.getId_benefit());
        assertThat(optionalBenefit).isPresent(); // Verify that the benefit is found

        Benefit retrievedBenefit = optionalBenefit.get();
        retrievedBenefit.setNom_benefit("updated transport");
        retrievedBenefit.setDescription_benefit("updated transport description");

        Benefit updatedBenefit = benefit_repo.save(retrievedBenefit);

        assertThat(updatedBenefit.getId_benefit()).isEqualTo(savedBenefit.getId_benefit()); // Verify ID remains the same
        assertThat(updatedBenefit.getNom_benefit()).isEqualTo("updated transport"); // Verify updated name
        assertThat(updatedBenefit.getDescription_benefit()).isEqualTo("updated transport description"); // Verify updated description

        // Intentional error: Uncomment the following line to intentionally fail the test
        // assertThat(updatedBenefit.getNom_benefit()).isEqualTo("incorrect value");


    }

    @Test
    public void testDeleteBenefit() {
        // Given
        Benefit benefit = Benefit.builder()
                .id_benefit(16)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

        benefit_repo.save(benefit);
        benefit_repo.deleteById(benefit.getId_benefit());
        Optional<Benefit> benefitReturn = benefit_repo.findById(benefit.getId_benefit());

        assertThat(benefitReturn).isEmpty();

    }



}





