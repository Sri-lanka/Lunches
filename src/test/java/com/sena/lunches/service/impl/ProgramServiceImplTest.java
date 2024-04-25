package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Program;
import com.sena.lunches.repository.Program_sena_repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ProgramServiceImplTest {

    @Mock
    private Program_sena_repo program_sena_repo;
    @InjectMocks
    private  ProgramServiceImpl programServiceImpl;

    @Test
    void getProgram() {
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        Program program2=Program.builder()
                .id_program(12)
                .name_program("english")
                .build();
        List<Program> mockProgram = Arrays.asList(program, program2);
        // Configure repository mock behavior
        when(program_sena_repo.findAll()).thenReturn(mockProgram);

        // When
        List<Program> retrievedProgram = programServiceImpl.getProgram();

        // Then
        assertThat(retrievedProgram).isNotNull();
        assertThat(retrievedProgram).hasSize(2);
        assertThat(retrievedProgram).contains(program, program2);
        // Verify repository method invocation
        verify(program_sena_repo, times(1)).findAll();
        
    }

    @Test
    void saveProgram() {
        Program programToSave=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        // Configure repository mock behavior
        when(program_sena_repo.save(Mockito.any(Program.class))).thenReturn(programToSave);
        // When
        Program savedfileSena = programServiceImpl.saveProgram(programToSave);

        // Then
        assertThat(savedfileSena).isNotNull();
        assertThat(savedfileSena.getId_program()).isEqualTo(10);
        assertThat(savedfileSena.getName_program()).isEqualTo("adso");
     
        // Verify repository method invocation
        verify(program_sena_repo, times(1)).save(any(Program.class));
        
    }

    @Test
    void getProgramById() {
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        // Configure repository mock behavior
        when(program_sena_repo.findById(10)).thenReturn(Optional.of(program));
        // When
        Program retrievedProgram = programServiceImpl.getProgramById(10);
        // Then
        assertThat(retrievedProgram).isNotNull();
        assertThat(retrievedProgram.getId_program()).isEqualTo(10);
        assertThat(retrievedProgram.getName_program()).isEqualTo("adso");
        // Verify repository method invocation
        verify(program_sena_repo, times(1)).findById(10);
        
    }

    @Test
    void updateProgram() {
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();

        when(program_sena_repo.findById(13)).thenReturn(Optional.of(program));
        when(program_sena_repo.save(Mockito.any(Program.class))).thenReturn(program);
        // When
        Program updateProgram = programServiceImpl.updateProgram(13, program);
        // Then
        assertThat(updateProgram).isNotNull();
        
    }

    @Test
    void deleteProgram() {
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();

        programServiceImpl.deleteProgram(10);

        verify(program_sena_repo, times(1)).deleteById(10);

    }
}