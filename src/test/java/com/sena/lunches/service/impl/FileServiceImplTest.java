package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Benefit;
import com.sena.lunches.entities.File_sena;
import com.sena.lunches.repository.File_sena_repo;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {
    @Mock
    private File_sena_repo file_sena_repo;
    @InjectMocks
    private FileServiceImpl fileServiceImpl;

    @Test
    void getFile_sena() {
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
        List<File_sena> mockFile_sena = Arrays.asList(file_sena, file_sena2);

        // Configure repository mock behavior
        when(file_sena_repo.findAll()).thenReturn(mockFile_sena);

        // When
        List<File_sena> retrievedFile_sena = fileServiceImpl.getFile_sena();

        // Then
        assertThat(retrievedFile_sena).isNotNull();
        assertThat(retrievedFile_sena).hasSize(2);
        assertThat(retrievedFile_sena).contains(file_sena, file_sena2);
        // Verify repository method invocation
        verify(file_sena_repo, times(1)).findAll();


    }
    @Test
    void saveFile_sena() {
        File_sena fileSenaToSave=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        // Configure repository mock behavior
        when(file_sena_repo.save(Mockito.any(File_sena.class))).thenReturn(fileSenaToSave);
        // When
        File_sena savedfileSena = fileServiceImpl.saveFile_sena(fileSenaToSave);

        // Then
        assertThat(savedfileSena).isNotNull();
        assertThat(savedfileSena.getId_file()).isEqualTo(10);
        assertThat(savedfileSena.getN_file()).isEqualTo(1);
        assertThat(savedfileSena.getId_program()).isEqualTo(4);
        // Verify repository method invocation
        verify(file_sena_repo, times(1)).save(any(File_sena.class));


    }
    @Test
    void getFile_senaById() {
        File_sena file_sena=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        // Configure repository mock behavior
        when(file_sena_repo.findById(10)).thenReturn(Optional.of(file_sena));
        // When
        File_sena retrievedFile_sena = fileServiceImpl.getFile_senaById(10);
        // Then
        assertThat(retrievedFile_sena).isNotNull();
        assertThat(retrievedFile_sena.getId_file()).isEqualTo(10);
        assertThat(retrievedFile_sena.getN_file()).isEqualTo(1);
        // Verify repository method invocation
        verify(file_sena_repo, times(1)).findById(10);



    }
    @Test
    void updateFile_sena() {
        File_sena fileSenaUpdate=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        when(file_sena_repo.findById(13)).thenReturn(Optional.of(fileSenaUpdate));
        when(file_sena_repo.save(Mockito.any(File_sena.class))).thenReturn(fileSenaUpdate);
        // When
        File_sena updateFile_sena = fileServiceImpl.updateFile_sena(13, fileSenaUpdate);
        // Then
        assertThat(updateFile_sena).isNotNull();


    }
    @Test
    void deleteFile_sena() {
        File_sena fileSenaDelate=File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        fileServiceImpl.deleteFile_sena(15);

        verify(file_sena_repo, times(1)).deleteById(15);



    }


}