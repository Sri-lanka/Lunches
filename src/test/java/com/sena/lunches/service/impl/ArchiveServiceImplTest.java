package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Archive;
import com.sena.lunches.entities.Benefit;
import com.sena.lunches.repository.Archive_repo;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ArchiveServiceImplTest {
    @Mock
    private Archive_repo archiveRepo;

    @InjectMocks
    private ArchiveServiceImpl archiveServiceImpl;
    @Test
    void getBenefit() {
        // Given
        Archive archive1 = new Archive();
        archive1.setId_archive(1);
        archive1.setTypeDoc("application/pdf");
        archive1.setName_archive("Identity card");
        archive1.setArchive_pdf("Mock PDF content".getBytes());

        Archive archive2 = new Archive();
        archive2.setId_archive(2);
        archive2.setTypeDoc("application/pdf");
        archive2.setName_archive("Identity card 2");
        archive2.setArchive_pdf("Mock PDF content 2".getBytes());

        List<Archive> mockArchive = Arrays.asList(archive1, archive2);

        // Configure repository mock behavior
        when(archiveRepo.findAll()).thenReturn(mockArchive);

        List<Archive> retrievedArchive = archiveServiceImpl.getArchive();

        // Then
        assertThat(retrievedArchive).isNotNull();
        assertThat(retrievedArchive).hasSize(2);
        assertThat(retrievedArchive).contains(archive1, archive2);

        // Verify repository method invocation
        verify(archiveRepo, times(1)).findAll();
    }

   @Test
    void store() throws IOException {
        // Arrange
        String fileName = "example.pdf";
        String contentType = "application/pdf";
        byte[] content = "Mock PDF content".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile(fileName, fileName, contentType, content);

        // Act
        archiveServiceImpl.store(mockMultipartFile);

        // Assert
        verify(archiveRepo).save(new Archive(fileName, contentType, content));
       // Assert
       assertNotNull(mockMultipartFile);
    }



    @Test
    void updateArchive() throws IOException {
        // Arrange
        int id = 1;
        String fileName = "example.pdf";
        String contentType = "application/pdf";
        byte[] content = "Mock PDF content".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile(fileName, fileName, contentType, content);

        // Simulate repository findById failure
        when(archiveRepo.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertNull(archiveServiceImpl.updateArchive(id, mockMultipartFile));
    }

    @Test
    void getArchiveById() {

        // Given
        Archive archive = new Archive();
        archive.setId_archive(1);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card");
        archive.setArchive_pdf("Mock PDF content".getBytes());

        when(archiveRepo.findById(1)).thenReturn(Optional.of(archive));

        // When
        Archive retrievedArchive = archiveServiceImpl.getArchiveById(1);

        // Then
        assertThat(retrievedArchive).isNotNull();
        assertThat(retrievedArchive.getId_archive()).isEqualTo(1);
        assertThat(retrievedArchive.getName_archive()).isEqualTo("Identity card");

        // Verify repository method invocation
        verify(archiveRepo, times(1)).findById(1);
    }


    @Test
    void deleteArchive() {
        // Given
        Archive archive = new Archive();
        archive.setId_archive(1);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card");
        archive.setArchive_pdf("Mock PDF content".getBytes());

        // Mockeying the repository to return the object when searching for ID 1
        // Call the method to remove the profit with ID
        archiveServiceImpl.deleteArchive(1);

        // Verify that the delete method of the repository has been called with the correct Archive
        verify(archiveRepo, times(1)).deleteById(1);
    }
}