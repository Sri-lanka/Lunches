package com.sena.lunches.service.impl;

import com.sena.lunches.entities.User_file;
import com.sena.lunches.repository.User_file_sena_repo;
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
class UserFileServiceImplTest {

    @Mock
    private User_file_sena_repo user_file_sena_repo;
    @InjectMocks
    private UserFileServiceImpl user_fileServiceImpl;
    
    @Test
    void getUser_file() {
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
        List<User_file> mockUser_file = Arrays.asList(user_file, user_file2);

        // Configure repository mock behavior
        when(user_file_sena_repo.findAll()).thenReturn(mockUser_file);

        // When
        List<User_file> retrievedUser_file = user_fileServiceImpl.getUser_file();

        // Then
        assertThat(retrievedUser_file).isNotNull();
        assertThat(retrievedUser_file).hasSize(2);
        assertThat(retrievedUser_file).contains(user_file, user_file2);
        // Verify repository method invocation
        verify(user_file_sena_repo, times(1)).findAll();

    }

    @Test
    void saveUser_file() {
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // Configure repository mock behavior
        when(user_file_sena_repo.save(Mockito.any(User_file.class))).thenReturn(user_file);
        // When
        User_file savedfileSena = user_fileServiceImpl.saveUser_file(user_file);

        // Then
        assertThat(savedfileSena).isNotNull();
        assertThat(savedfileSena.getId_user_file()).isEqualTo(10);
        assertThat(savedfileSena.getAsset()).isEqualTo(1);

        // Verify repository method invocation
        verify(user_file_sena_repo, times(1)).save(any(User_file.class));


    }

    @Test
    void getUser_fileById() {
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // Configure repository mock behavior
        when(user_file_sena_repo.findById(10)).thenReturn(Optional.of(user_file));
        // When
        User_file retrievedUser_file = user_fileServiceImpl.getUser_fileById(10);
        // Then
        assertThat(retrievedUser_file).isNotNull();
        assertThat(retrievedUser_file.getId_user_file()).isEqualTo(10);
        assertThat(retrievedUser_file.getAsset()).isEqualTo(1);
        // Verify repository method invocation
        verify(user_file_sena_repo, times(1)).findById(10);
        
    }

    @Test
    void updateUser_file() {
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();

        when(user_file_sena_repo.findById(13)).thenReturn(Optional.of(user_file));
        when(user_file_sena_repo.save(Mockito.any(User_file.class))).thenReturn(user_file);
        // When
        User_file updateUser_file = user_fileServiceImpl.updateUser_file(13, user_file);
        // Then
        assertThat(updateUser_file).isNotNull();
        
    }

    @Test
    void deleteUser_file() {
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();

        user_fileServiceImpl.deleteUser_file(10);

        verify(user_file_sena_repo, times(1)).deleteById(10);


    }
}