package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Assistance;
import com.sena.lunches.entities.Benefit;
import com.sena.lunches.repository.Assistance_repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AssistanceServiceImplTest {

    @Mock
    private Assistance_repo assistance_repo;

    @InjectMocks
    private AssistanceServiceImpl assistanceServiceImpl;

    @Test
    void getAssistance() {
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

        List<Assistance> mockAssistance = Arrays.asList(assistance, assistance2);

        // Configure repository mock behavior
        when(assistance_repo.findAll()).thenReturn(mockAssistance);

        // When
        List<Assistance> retrievedAssistance = assistanceServiceImpl.getAssistance();

        // Then
        assertThat(retrievedAssistance).isNotNull();
        assertThat(retrievedAssistance).hasSize(2);
        assertThat(retrievedAssistance).contains(assistance, assistance2);

        // Verify repository method invocation
        verify(assistance_repo, times(1)).findAll();
    }

    @Test
    void saveAssistance() {
        // Given
        Assistance assistanceToSave = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        // Configure repository mock behavior
        when(assistance_repo.save(Mockito.any(Assistance.class))).thenReturn(assistanceToSave);

        // When
        Assistance savedAssistance = assistanceServiceImpl.saveAssistance(assistanceToSave);

        // Then
        assertThat(savedAssistance).isNotNull();
        assertThat(savedAssistance.getId_assistance()).isEqualTo(16);
        assertThat(savedAssistance.getId_authorization()).isEqualTo(20);

        // Verify repository method invocation
        verify(assistance_repo, times(1)).save(any(Assistance.class));
    }

    @Test
    void getAssistanceById() {
        // Given
        Assistance assistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        // Configure repository mock behavior
        when(assistance_repo.findById(16)).thenReturn(Optional.of(assistance));

        // When
        Assistance retrievedAssistance = assistanceServiceImpl.getAssistanceById(16);

        // Then
        assertThat(retrievedAssistance).isNotNull();
        assertThat(retrievedAssistance.getId_assistance()).isEqualTo(16);
        assertThat(retrievedAssistance.getId_authorization()).isEqualTo(20);

        // Verify repository method invocation
        verify(assistance_repo, times(1)).findById(16);
    }

    @Test
    void updateAssistance() {
        // Given
        Assistance assistanceUpdate = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        when(assistance_repo.findById(16)).thenReturn(Optional.of(assistanceUpdate));
        when(assistance_repo.save(Mockito.any(Assistance.class))).thenReturn(assistanceUpdate);

        // When
        Assistance updateAssistance = assistanceServiceImpl.updateAssistance(16, assistanceUpdate);

        // Then
        assertThat(updateAssistance).isNotNull();
    }

    @Test
    void deleteAssistance() {
        // Given
        Assistance assistanceDelete = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        // Mockear el repositorio para que devuelva el objeto cuando se busque por el ID 15


        // Llamar al método para eliminar el beneficio con ID 15
        assistanceServiceImpl.deleteAssistance(16);

        // Verificar que se haya llamado al método delete del repositorio con el beneficio correcto
        verify(assistance_repo, times(1)).deleteById(16);
    }
}