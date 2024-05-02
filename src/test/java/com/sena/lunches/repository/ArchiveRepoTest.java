package com.sena.lunches.repository;

import com.sena.lunches.entities.Archive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.nio.ByteBuffer;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Rollback(false)

public class ArchiveRepoTest {
    @Autowired
    private Archive_repo archive_repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveBenefit() {

        byte[] byteArray = new byte[10];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) i;
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] byteArrayPdf = new byte[buffer.remaining()];
        buffer.get(byteArrayPdf);

        // Given
        Archive archive = new Archive();
        archive.setId_archive(16);
        archive.setTypeDoc("transport");
        archive.setName_archive("use transport");
        archive.setArchive_pdf(byteArrayPdf);

        // When
        Archive savedArchive = entityManager.persist(archive);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedArchive.getId_archive()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the benefit can be retrieved from the repository

        Optional<Archive> retrievedArchive = archive_repo.findById(savedArchive.getId_archive());
        assertThat(retrievedArchive).isPresent(); // Check if benefit is found
        assertThat(retrievedArchive.get().getName_archive()).isEqualTo("use transport"); // Verify attribute value
    }

/*
  

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
        benefit_repo.save(benefit2);

        List<Benefit> benefitList = benefit_repo.findAll();

        assertThat(benefitList).isNotNull();
        assertThat(benefitList.size()).isEqualTo(4);
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
                .id_benefit(15)
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
*/


}





