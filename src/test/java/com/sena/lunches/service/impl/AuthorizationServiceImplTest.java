package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Authorization;
import com.sena.lunches.entities.Benefit;
import com.sena.lunches.repository.Authorization_sena_repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AuthorizationServiceImplTest {

    @Mock
    private Authorization_sena_repo authorization_sena_repo;

    @InjectMocks
    private AuthorizationServiceImpl authorizationServiceImpl;

    @Test
    void getAuthorization() {
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

        List<Authorization> mockAuthorization = Arrays.asList(authorization, authorization2);

        // Configure repository mock behavior
        when(authorization_sena_repo.findAll()).thenReturn(mockAuthorization);

        // When
        List<Authorization> retrievedAuthorization = authorizationServiceImpl.getAuthorization();

        // Then
        assertThat(retrievedAuthorization).isNotNull();
        assertThat(retrievedAuthorization).hasSize(2);
        assertThat(retrievedAuthorization).contains(authorization, authorization2);

        // Verify repository method invocation
        verify(authorization_sena_repo, times(1)).findAll();

    }

    @Test
    void saveAuthorization() {
        // Given
        Authorization authorizationToSave = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // Configure repository mock behavior
        when(authorization_sena_repo.save(Mockito.any(Authorization.class))).thenReturn(authorizationToSave);

        // When
        Authorization savedAuthorization = authorizationServiceImpl.saveAuthorization(authorizationToSave);

        // Then
        assertThat(savedAuthorization).isNotNull();
        assertThat(savedAuthorization.getId_authorization()).isEqualTo(16);
        assertThat(savedAuthorization.getId_user()).isEqualTo(20);
        assertThat(savedAuthorization.getDescription_excuse()).isEqualTo("description");

        // Verify repository method invocation
        verify(authorization_sena_repo, times(1)).save(any(Authorization.class));
    }

    @Test
    void getAuthorizationById() {
        // Given
        Authorization authorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // Configure repository mock behavior
        when(authorization_sena_repo.findById(16)).thenReturn(Optional.of(authorization));

        // When
        Authorization retrievedAuthorization = authorizationServiceImpl.getAuthorizationById(16);

        // Then
        assertThat(retrievedAuthorization).isNotNull();
        assertThat(retrievedAuthorization.getId_authorization()).isEqualTo(16);
        assertThat(retrievedAuthorization.getId_user()).isEqualTo(20);

        // Verify repository method invocation
        verify(authorization_sena_repo, times(1)).findById(16);
    }

    @Test
    void updateAuthorization() {
        // Given
        Authorization authorizationUpdate = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        when(authorization_sena_repo.findById(16)).thenReturn(Optional.of(authorizationUpdate));
        when(authorization_sena_repo.save(Mockito.any(Authorization.class))).thenReturn(authorizationUpdate);

        // When
        Authorization updateAuthorization = authorizationServiceImpl.updateAuthorization(16, authorizationUpdate);

        // Then
        assertThat(updateAuthorization).isNotNull();
    }

    @Test
    void deleteAuthorization() {
        // Given
        Authorization authorizationDelete = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // Mockear el repositorio para que devuelva el objeto cuando se busque por el ID 15


        // Llamar al método para eliminar el beneficio con ID 15
        authorizationServiceImpl.deleteAuthorization(16);

        // Verificar que se haya llamado al método delete del repositorio con el beneficio correcto
        verify(authorization_sena_repo, times(1)).deleteById(16);

    }
}