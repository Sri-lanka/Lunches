package com.sena.lunches.repository;

import com.sena.lunches.entities.Benefit;
import com.sena.lunches.entities.File_sena;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Rollback(false)
class File_sena_repoTest {
    @Autowired
    private File_sena_repo file_sena_repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveFile_sena() {
        // Given
        File_sena file_sena=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();

        // When
        File_sena savedFileSena = entityManager.persist(file_sena);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedFileSena.getId_file()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the benefit can be retrieved from the repository

        Optional<File_sena> retrievedBenefit = file_sena_repo.findById(savedFileSena.getId_file());
        assertThat(retrievedBenefit).isPresent(); // Check if benefit is found
        assertThat(retrievedBenefit.get().getN_file()).isEqualTo(1); // Verify attribute value
    }

    @Test
    public void testGetFile_sena(){
        // Given
        File_sena file_sena=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        File_sena file_sena2=File_sena.builder()
                .id_file(11)
                .n_file(2)
                .id_program(6)
                .build();

        file_sena_repo.save(file_sena);
        file_sena_repo.save(file_sena2);

        List<File_sena> fileSenaListList = file_sena_repo.findAll();

        assertThat(fileSenaListList).isNotNull();
        assertThat(fileSenaListList.size()).isEqualTo(2);
    }
    @Test
    public void testFindFile_sena() {
        // Given
        File_sena file_sena=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        // When
        File_sena savedFileSena = file_sena_repo.save(file_sena);

        // Then
        assertThat(savedFileSena.getId_file()).isNotNull(); // Verifica que el ID se haya asignado correctamente

        Optional<File_sena> retrievedFileSena = file_sena_repo.findById(savedFileSena.getId_file());

        assertThat(retrievedFileSena).isPresent();
        File_sena retrievedBenefit = retrievedFileSena.get();
        assertThat(retrievedBenefit.getN_file()).isEqualTo(1); // Verifica el nombre del beneficio

    }

    @Test
    public void testUpdateFile_sena() {
        // Given
        File_sena file_sena=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();

        File_sena savedFile_sena = file_sena_repo.save(file_sena);

        // Retrieve the saved benefit from the repository
        Optional<File_sena> optionalFile_sena = file_sena_repo.findById(savedFile_sena.getId_file());
        assertThat(optionalFile_sena).isPresent(); // Verify that the benefit is found

        File_sena retrievedBenefit = optionalFile_sena.get();
        retrievedBenefit.setN_file(2);
        retrievedBenefit.setId_program(5);

        File_sena updatedBenefit = file_sena_repo.save(retrievedBenefit);

        assertThat(updatedBenefit.getId_file()).isEqualTo(savedFile_sena.getId_file()); // Verify ID remains the same
        assertThat(updatedBenefit.getN_file()).isEqualTo(2); // Verify updated name
        assertThat(updatedBenefit.getId_program()).isEqualTo(5); // Verify updated description

    }

    @Test
    public void testDeleteBenefit() {
        // Given
        File_sena file_sena=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();

        file_sena_repo.save(file_sena);
        file_sena_repo.deleteById(file_sena.getId_file());
        Optional<File_sena> file_senaReturn = file_sena_repo.findById(file_sena.getId_file());

        assertThat(file_senaReturn).isEmpty();

    }







}