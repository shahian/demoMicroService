package com.shahian.mscustomer.service;

import com.shahian.mscustomer.model.User;
import com.shahian.mscustomer.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        // Arrange
        User user = new User();
        user.setUserName("John Doe");
        user.setEmail("johndoe@example.com");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User createdUser = userService.save(user);

        // Assert
        assertEquals(createdUser, user);
        verify(userRepository).save(user);
    }

    @Test
    public void testFindUserById() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<User> foundUser = Optional.ofNullable(userService.findById(userId));

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals(foundUser.get(), user);
        verify(userRepository).findById(userId);
    }
    /**
     * This method is used to update test user   from Service layer
     *
     * verify(mockedObject).method(arguments) is a method from the Mockito framework that is used to verify if a specific method on a mocked object was called with specific arguments. In this case, the mockedObject is the userRepository and the method is save(user).
     *      This verification is used to check if the save method on the userRepository was called with the user object that was passed to the updateUser method. If the verification fails, then it means that the save method was not called on the userRepository or it was called with a different object.
     *      It also used to check if the method was called the number of times it was expected. If the method was not called or it was called more or fewer times than expected, the test case will fail and it will be reported as an error.
     *      In this example, the assertion is checking if the save method of the userRepository was called with the user object that was passed to the updateUser method. If it is not called, the test case will fail.
     *      In other words, it allows the developer to check if the userRepository.save() has been called with the user object during the execution of the updateUser method.
     * assertNotNull(user) => check if user object is not null before passing to update method
     * assertEquals(updatedUser, user) : This assertion is used to check if the user object that is passed to the updateUser method is the same as the user object that is returned by the updateUser method. If the assertion fails, then it means that the updateUser method did not update the user object correctly, and it is returning a different object.
     * It also used to check if both the expected and actual values are equals. If they are not, the test case will fail and it will be reported as an error.

     */
    @Test
    public void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        assertNotNull(user);
        // Act
        User updatedUser = userService.update(1L, user);

        // Assert
        assertEquals(updatedUser, user);

        verify(userRepository).save(user);
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);
        // Act
        userService.delete(user);

        // Assert
        verify(userRepository).delete(user);
    }
}
