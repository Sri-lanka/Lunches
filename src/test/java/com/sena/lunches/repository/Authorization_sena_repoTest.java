package com.sena.lunches.repository;


import com.sena.lunches.entities.Assistance;
import com.sena.lunches.entities.Authorization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Rollback(false)

public class Authorization_sena_repoTest {

    @Autowired
    private Authorization_sena_repo authorization_sena_repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveAuthorization() {
        // Given
        Authorization authorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // When
        Authorization savedAuthorization = entityManager.persist(authorization);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedAuthorization.getId_authorization()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the authorization can be retrieved from the repository
        Optional<Authorization> retrievedAuthorization = authorization_sena_repo.findById(savedAuthorization.getId_authorization());
        assertThat(retrievedAuthorization).isPresent(); // Check if authorization is found
        assertThat(retrievedAuthorization.get().getId_user()).isEqualTo(20); // Verify attribute value
    }

    @Test
    public void testGetAuthorization(){
        // Given
        Authorization authorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        Authorization authorization2 = Authorization.builder()
                .id_authorization(17)
                .id_user(21)
                .id_user_autho(16)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        authorization_sena_repo.save(authorization);
        authorization_sena_repo.save(authorization2);

        List<Authorization>authorizationList = authorization_sena_repo.findAll();

        assertThat(authorizationList).isNotNull();
        assertThat(authorizationList.size()).isEqualTo(2);
    }

    @Test
    public void testFindAuthorization(){
        // Given
        Authorization authorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // When
        Authorization savedAuthorization = authorization_sena_repo.save(authorization);

        // Then
        assertThat(savedAuthorization.getId_authorization()).isNotNull(); // Verifica que el ID se haya asignado correctamente

        Optional<Authorization> retrievedAuthorizationOptional = authorization_sena_repo.findById(savedAuthorization.getId_authorization());

        assertThat(retrievedAuthorizationOptional).isPresent();
        Authorization retrievedAuthorization = retrievedAuthorizationOptional.get();
        assertThat(retrievedAuthorization.getId_user()).isEqualTo(20); // Verifica el nombre del beneficio

    }

    @Test
    public void testUpdateAuthorization(){
        // Given
        Authorization authorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        Authorization savedAuthorization = authorization_sena_repo.save(authorization);

        // Retrieve the saved benefit from the repository
        Optional<Authorization> optionalAuthorization = authorization_sena_repo.findById(savedAuthorization.getId_authorization());
        assertThat(optionalAuthorization).isPresent(); // Verify that the benefit is found

        Authorization retrievedAuthorization = optionalAuthorization.get();
        retrievedAuthorization.setId_user(20);


        Authorization updatedAuthorization = authorization_sena_repo.save(retrievedAuthorization);

        assertThat(updatedAuthorization.getId_authorization()).isEqualTo(savedAuthorization.getId_authorization()); // Verify ID remains the same
        assertThat(updatedAuthorization.getId_user()).isEqualTo(20); // Verify updated name

    }
    @Test
    public void testDeleteAuthorization(){
        // Given
        Authorization authorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        authorization_sena_repo.save(authorization);
        authorization_sena_repo.deleteById(authorization.getId_authorization());
        Optional<Authorization> authorizationReturn = authorization_sena_repo.findById(authorization.getId_authorization());

        assertThat(authorizationReturn).isEmpty();
    }
}