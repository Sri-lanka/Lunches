package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Benefit;
import com.sena.lunches.repository.Benefit_repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class BenefitServiceImplTest {

    @Mock
    private Benefit_repo benefit_repo;

    @InjectMocks
    private BenefitServiceImpl benefitServiceImpl;


    @Test
    void getBenefit() {
        // Given
        Benefit benefit1 = Benefit.builder()
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

        List<Benefit> mockBenefits = Arrays.asList(benefit1, benefit2);

        // Configure repository mock behavior
        when(benefit_repo.findAll()).thenReturn(mockBenefits);

        // When
        List<Benefit> retrievedBenefits = benefitServiceImpl.getBenefit();

        // Then
        assertThat(retrievedBenefits).isNotNull();
        assertThat(retrievedBenefits).hasSize(2);
        assertThat(retrievedBenefits).contains(benefit1, benefit2);

        // Verify repository method invocation
        verify(benefit_repo, times(1)).findAll();
    }
    @Test
    void saveBenefit() {
        Benefit benefitToSave = Benefit.builder()
                .id_benefit(14)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();
        // Configure repository mock behavior
        when(benefit_repo.save(Mockito.any(Benefit.class))).thenReturn(benefitToSave);

        // When
        Benefit savedBenefit = benefitServiceImpl.saveBenefit(benefitToSave);

        // Then
        assertThat(savedBenefit).isNotNull();
        assertThat(savedBenefit.getId_benefit()).isEqualTo(14);
        assertThat(savedBenefit.getNom_benefit()).isEqualTo("transport");
        assertThat(savedBenefit.getDescription_benefit()).isEqualTo("use transport");

        // Verify repository method invocation
        verify(benefit_repo, times(1)).save(any(Benefit.class));
    }
    @Test
    void getBenefitById() {
        Benefit benefit1 = Benefit.builder()
                .id_benefit(13)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

        // Configure repository mock behavior
        when(benefit_repo.findById(13)).thenReturn(Optional.of(benefit1));

        // When
        Benefit retrievedBenefit = benefitServiceImpl.getBenefitById(13);

        // Then
        assertThat(retrievedBenefit).isNotNull();
        assertThat(retrievedBenefit.getId_benefit()).isEqualTo(13);
        assertThat(retrievedBenefit.getNom_benefit()).isEqualTo("transport");

        // Verify repository method invocation
        verify(benefit_repo, times(1)).findById(13);
    }
    @Test
    void updateBenefit() {
        Benefit benefitUpdate = Benefit.builder()
                .id_benefit(13)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

        when(benefit_repo.findById(13)).thenReturn(Optional.of(benefitUpdate));
        when(benefit_repo.save(Mockito.any(Benefit.class))).thenReturn(benefitUpdate);

        // When
        Benefit updateBenefit = benefitServiceImpl.updateBenefit(13, benefitUpdate);

        // Then
        assertThat(updateBenefit).isNotNull();

    }

    @Test
    public void deleteBenefit() {
        // Crear el objeto Benefit a ser eliminado
        Benefit benefitToDelete1 = Benefit.builder()
                .id_benefit(15)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

        // Mockear el repositorio para que devuelva el objeto cuando se busque por el ID 15


        // Llamar al método para eliminar el beneficio con ID 15
        benefitServiceImpl.deleteBenefit(15);

        // Verificar que se haya llamado al método delete del repositorio con el beneficio correcto
        verify(benefit_repo, times(1)).deleteById(15);
    }


}