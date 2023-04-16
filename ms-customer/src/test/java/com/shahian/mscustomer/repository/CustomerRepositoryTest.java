package com.shahian.mscustomer.repository;

import com.shahian.mscustomer.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CustomerRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        // Arrange
        User user = new User();
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");
        // Act
        userRepository.save(user);
        // Assert
        assertNotNull(user.getId());
        assertEquals(userRepository.findById(user.getId()).get(), user);
    }

    @Test
    public void testFindById() {
        // Arrange
        User user = new User();
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");
        userRepository.save(user);
        // Act
        User  foundUser = userRepository.findById(user.getId()).get();
        // Assert
        assertEquals(foundUser, user);
    }

    @Test
    public void testDelete() {
        // Arrange
        User user = new User();
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");
        userRepository.save(user);
        // Act
        userRepository.delete(user);
        // Assert
        assertFalse(userRepository.findById(user.getId()).isPresent());
    }
}
