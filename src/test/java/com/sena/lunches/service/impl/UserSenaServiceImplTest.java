package com.sena.lunches.service.impl;

import com.sena.lunches.entities.User_sena;
import com.sena.lunches.entities.User_sena;
import com.sena.lunches.repository.User_sena_repo;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserSenaServiceImplTest {
    @Mock
    private User_sena_repo user_sena_repo;
    @InjectMocks
    private UserSenaServiceImpl userSenaServiceImpl;

    @Test
    void getUser_sena() {
        // Given
        User_sena user_sena=User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();
        User_sena user_sena2=User_sena.builder()
                .id_user(12)
                .document(12)
                .type_document(2)
                .rol(1)
                .name_1("bl")
                .name_2("ack")
                .last_name_1("df")
                .last_name_2("as")
                .age(17)
                .Email("sag@gmail.com")
                .telephone(3136549872L)
                .keyword("4321")
                .state(0)
                .id_benefit(14)
                .build();
        List<User_sena> mockUser_sena = Arrays.asList(user_sena, user_sena2);

        // Configure repository mock behavior
        when(user_sena_repo.findAll()).thenReturn(mockUser_sena);

        // When
        List<User_sena> retrievedUser_sena = userSenaServiceImpl.getUser_sena();

        // Then
        assertThat(retrievedUser_sena).isNotNull();
        assertThat(retrievedUser_sena).hasSize(2);
        assertThat(retrievedUser_sena).contains(user_sena, user_sena2);
        // Verify repository method invocation
        verify(user_sena_repo, times(1)).findAll();

    }

    @Test
    void saveUser_sena() {
        User_sena user_sena=User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();
        // Configure repository mock behavior
        when(user_sena_repo.save(Mockito.any(User_sena.class))).thenReturn(user_sena);
        // When
        User_sena savedfileSena = userSenaServiceImpl.saveUser_sena(user_sena);

        // Then
        assertThat(savedfileSena).isNotNull();
        assertThat(savedfileSena.getId_user()).isEqualTo(11);
        assertThat(savedfileSena.getState()).isEqualTo(1);

        // Verify repository method invocation
        verify(user_sena_repo, times(1)).save(any(User_sena.class));


    }

    @Test
    void getUser_senaById() {
        User_sena user_sena=User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();
        // Configure repository mock behavior
        when(user_sena_repo.findById(11)).thenReturn(Optional.of(user_sena));
        // When
        User_sena retrievedUser_sena = userSenaServiceImpl.getUser_senaById(11);
        // Then
        assertThat(retrievedUser_sena).isNotNull();
        assertThat(retrievedUser_sena.getId_user()).isEqualTo(11);
        assertThat(retrievedUser_sena.getState()).isEqualTo(1);
        // Verify repository method invocation
        verify(user_sena_repo, times(1)).findById(11);

    }

    @Test
    void updateUser_sena() {
        User_sena user_sena=User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();

        when(user_sena_repo.findById(13)).thenReturn(Optional.of(user_sena));
        when(user_sena_repo.save(Mockito.any(User_sena.class))).thenReturn(user_sena);
        // When
        User_sena updateUser_sena = userSenaServiceImpl.updateUser_sena(13, user_sena);
        // Then
        assertThat(updateUser_sena).isNotNull();

    }

    @Test
    void deleteUser_sena() {
        User_sena user_sena=User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();

        userSenaServiceImpl.deleteUser_sena(10);

        verify(user_sena_repo, times(1)).deleteById(10);


    }
}