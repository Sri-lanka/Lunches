package com.sena.lunches.repository;


import com.sena.lunches.entities.User_sena;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Rollback(false)
class User_sena_repoTest {
    @Autowired
    private User_sena_repo user_sena_repo;
    @Autowired
    private TestEntityManager entityManager;

   @Test
    public void testSaveUser_sena() {
        // Given
        User_sena user_sena=User_sena.builder()
                .id_user(12)
                .document(11)
                .type_document(1)
                .roles(1)
                .nameUser("blue")
                .lastName("asdf")
                .email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .build();
        // When
        User_sena savedUser_sena = entityManager.persist(user_sena);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedUser_sena.getId_user()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the user_sena can be retrieved from the repository
        Optional<User_sena> retrievedUser_sena = user_sena_repo.findById(savedUser_sena.getId_user());
        assertThat(retrievedUser_sena).isPresent(); // Check if user_sena is found
        assertThat(retrievedUser_sena.get().getState()).isEqualTo(1); // Verify attribute value
        
    }
   /* @Test
    public void testGetUser_sena(){
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

        user_sena_repo.save(user_sena);
        user_sena_repo.save(user_sena2);

        List<User_sena> user_senaListList = user_sena_repo.findAll();

        assertThat(user_senaListList).isNotNull();
        assertThat(user_senaListList.size()).isEqualTo(2);
    }

    @Test
    public void testFindUser_sena() {
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
        // When
        User_sena savedUser_sena = user_sena_repo.save(user_sena);

        // Then
        assertThat(savedUser_sena.getId_user()).isNotNull(); //Verify that the ID has been assigned correctly

        Optional<User_sena> retrievedUser_sena = user_sena_repo.findById(savedUser_sena.getId_user());

        assertThat(retrievedUser_sena).isPresent();
        User_sena retrieved_User_sena=retrievedUser_sena.get();

        assertThat(retrieved_User_sena.getState()).isEqualTo(1); // Check the user_sena name

    }

    @Test
    public void testUpdateUser_sena() {
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

        User_sena savedUser_sena = user_sena_repo.save(user_sena);

        // Retrieve the saved user_sena from the repository
        Optional<User_sena> optionalUser_sena = user_sena_repo.findById(savedUser_sena.getId_user());
        assertThat(optionalUser_sena).isPresent(); // Verify that the user_sena is found

        User_sena retrievedUser_sena = optionalUser_sena.get();
        retrievedUser_sena.setState(2);


        User_sena updatedUser_sena = user_sena_repo.save(retrievedUser_sena);

        assertThat(updatedUser_sena.getId_user()).isEqualTo(savedUser_sena.getId_user()); // Verify ID remains the same
        assertThat(updatedUser_sena.getState()).isEqualTo(2); // Verify updated name

    }
    @Test
    public void testDeleteUser_sena() {
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

        user_sena_repo.save(user_sena);
        user_sena_repo.deleteById(user_sena.getId_user());
        Optional<User_sena> file_senaReturn = user_sena_repo.findById(user_sena.getId_user());

        assertThat(file_senaReturn).isEmpty();

    }
    
    */
    

}