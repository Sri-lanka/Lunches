package com.sena.lunches.repository;

import com.sena.lunches.entities.Program;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Rollback(false)
class Program_sena_repoTest {
    @Autowired
    private Program_sena_repo program_sena_repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveProgram() {
        // Given
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        // When
        Program savedprogram = entityManager.persist(program);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedprogram.getId_program()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the program can be retrieved from the repository
        Optional<Program> retrievedProgram = program_sena_repo.findById(savedprogram.getId_program());
        assertThat(retrievedProgram).isPresent(); // Check if program is found
        assertThat(retrievedProgram.get().getName_program()).isEqualTo("adso"); // Verify attribute value
    }
    @Test
    public void testGetProgram(){
        // Given
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        Program program2=Program.builder()
                .id_program(12)
                .name_program("english")
                .build();

        program_sena_repo.save(program);
        program_sena_repo.save(program2);

        List<Program> programListList = program_sena_repo.findAll();

        assertThat(programListList).isNotNull();
        assertThat(programListList.size()).isEqualTo(2);
    }

    @Test
    public void testFindProgram() {
        // Given
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        // When
        Program savedProgram = program_sena_repo.save(program);

        // Then
        assertThat(savedProgram.getId_program()).isNotNull(); //Verify that the ID has been assigned correctly

        Optional<Program> retrievedProgram = program_sena_repo.findById(savedProgram.getId_program());

        assertThat(retrievedProgram).isPresent();
        Program retrieved_Program=retrievedProgram.get();

        assertThat(retrieved_Program.getName_program()).isEqualTo("adso"); // Check the program name

    }

    @Test
    public void testUpdateProgram() {
        // Given
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();

        Program savedProgram = program_sena_repo.save(program);

        // Retrieve the saved program from the repository
        Optional<Program> optionalProgram = program_sena_repo.findById(savedProgram.getId_program());
        assertThat(optionalProgram).isPresent(); // Verify that the program is found

        Program retrievedProgram = optionalProgram.get();
        retrievedProgram.setName_program("english");


        Program updatedProgram = program_sena_repo.save(retrievedProgram);

        assertThat(updatedProgram.getId_program()).isEqualTo(savedProgram.getId_program()); // Verify ID remains the same
        assertThat(updatedProgram.getName_program()).isEqualTo("english"); // Verify updated name

    }

    @Test
    public void testDeleteProgram() {
        // Given
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();

        program_sena_repo.save(program);
        program_sena_repo.deleteById(program.getId_program());
        Optional<Program> file_senaReturn = program_sena_repo.findById(program.getId_program());

        assertThat(file_senaReturn).isEmpty();

    }

}