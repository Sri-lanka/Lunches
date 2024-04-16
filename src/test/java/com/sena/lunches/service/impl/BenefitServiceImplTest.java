package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Benefit;
import com.sena.lunches.repository.Benefit_repo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BenefitServiceImplTest {

    @Mock
    private Benefit_repo benefit_repo;

    @InjectMocks
    private BenefitServiceImpl benefitServiceImpl;

    private Benefit benefit;



    @BeforeEach
    void setUp() {
        benefit=Benefit.builder()
                .id_benefit(16)
                .nom_benefit("transport")
                .description_benefit("use transport")
                .date_start(LocalDate.of(2024, 6, 14))
                .date_end(LocalDate.of(2024, 12, 14))
                .build();

    }

    @Test
    void getBenefit() {
    }

    @Test
    void saveBenefit() {
    }

    @Test
    void getBenefitById() {
    }

    @Test
    void updateBenefit() {
    }

    @Test
    void deleteBenefit() {

    }
}