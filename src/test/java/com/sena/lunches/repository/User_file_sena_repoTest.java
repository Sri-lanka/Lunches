package com.sena.lunches.repository;

import com.sena.lunches.entities.User_file;

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

class User_file_sena_repoTest {
    @Autowired
    private User_file_sena_repo user_file_sena_repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveUser_file() {
        // Given
        User_file user_file=User_file.builder()
                .id_user_file(11)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // When
        User_file savedUser_file = entityManager.persist(user_file);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedUser_file.getId_user_file()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the user_file can be retrieved from the repository
        Optional<User_file> retrievedUser_file = user_file_sena_repo.findById(savedUser_file.getId_user_file());
        assertThat(retrievedUser_file).isPresent(); // Check if user_file is found
        assertThat(retrievedUser_file.get().getAsset()).isEqualTo(1); // Verify attribute value
    }
    @Test
    public void testGetUser_file(){
        // Given
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        User_file user_file2=User_file.builder()
                .id_user_file(11)
                .id_file(12)
                .id_user(13)
                .asset(0)
                .build();

        user_file_sena_repo.save(user_file);
        user_file_sena_repo.save(user_file2);

        List<User_file> user_fileListList = user_file_sena_repo.findAll();

        assertThat(user_fileListList).isNotNull();
        assertThat(user_fileListList.size()).isEqualTo(2);
    }
    @Test
    public void testFindUser_file() {
        // Given
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // When
        User_file savedUser_file = user_file_sena_repo.save(user_file);

        // Then
        assertThat(savedUser_file.getId_user_file()).isNotNull(); //Verify that the ID has been assigned correctly

        Optional<User_file> retrievedUser_file = user_file_sena_repo.findById(savedUser_file.getId_user_file());

        assertThat(retrievedUser_file).isPresent();
        User_file retrieved_User_file=retrievedUser_file.get();

        assertThat(retrieved_User_file.getAsset()).isEqualTo(1); // Check the user_file name

    }
    @Test
    public void testUpdateUser_file() {
        // Given
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();

        User_file savedUser_file = user_file_sena_repo.save(user_file);

        // Retrieve the saved user_file from the repository
        Optional<User_file> optionalUser_file = user_file_sena_repo.findById(savedUser_file.getId_user_file());
        assertThat(optionalUser_file).isPresent(); // Verify that the user_file is found

        User_file retrievedUser_file = optionalUser_file.get();
        retrievedUser_file.setAsset(2);


        User_file updatedUser_file = user_file_sena_repo.save(retrievedUser_file);

        assertThat(updatedUser_file.getId_user_file()).isEqualTo(savedUser_file.getId_user_file()); // Verify ID remains the same
        assertThat(updatedUser_file.getAsset()).isEqualTo(2); // Verify updated name

    }
    @Test
    public void testDeleteUser_file() {
        // Given
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();

        user_file_sena_repo.save(user_file);
        user_file_sena_repo.deleteById(user_file.getId_user_file());
        Optional<User_file> file_senaReturn = user_file_sena_repo.findById(user_file.getId_user_file());

        assertThat(file_senaReturn).isEmpty();

    }





}