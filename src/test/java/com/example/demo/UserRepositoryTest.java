package com.example.demo;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;
    private User user1;
    private User user2;

    @BeforeEach
    void setup(){
        userRepository.deleteAllInBatch();
        user1 = new User("Alice Wonderland", "alice@example.com");
        user2 = new User("Bob The builder" , "bob@example.com");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
        entityManager.clear();
    }
    @Test
    void testFindByEmailFound() throws Exception{
        Optional<User> foundUser = userRepository.findByEmail(user1.getEmail());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo(user1.getName());
        assertThat(foundUser.get().getEmail()).isEqualTo(user1.getEmail());
        assertThat(foundUser.get().getId()).isEqualTo(user1.getId());
    }

    @Test
    void testFindByEmailNotFound() throws Exception {
        Optional<User> notFound = userRepository.findByEmail("notemail@example.com");
        assertThat(notFound).isNotPresent();
//        assertThat(notFound.get().getName()).isNotEqualTo(user1.getName());
//        assertThat(notFound.get().getEmail()).isNotEqualTo(user1.getEmail());
//        assertThat(notFound.get().getId()).isNotEqualTo(user1.getId());
    }
}