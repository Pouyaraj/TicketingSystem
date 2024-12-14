package com.example.TicketingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TicketingSystem.entity.UserEntity;
import com.example.TicketingSystem.repository.UserRepository;
import com.example.TicketingSystem.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private UserEntity testUser;

    @BeforeEach
    public void setUp() {
        // Initialize the test user
        testUser = new UserEntity("testuser", "password123");
    }

    @Test
    public void testRegister_UserAlreadyExists() {
        // Arrange: Mock the repository to return a user if it already exists
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);

        // Act & Assert: Ensure IllegalArgumentException is thrown
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.register(testUser);
        });
        assertEquals("Username already exists. Please choose a different username.", thrown.getMessage());
    }

    @Test
    public void testRegister_SuccessfulRegistration() {
        // Arrange: Mock the repository to return null (meaning no user exists)
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(null);
        when(userRepository.save(testUser)).thenReturn(testUser);

        // Act: Call the register method
        UserEntity registeredUser = employeeService.register(testUser);

        // Assert: Check that the user was saved correctly
        assertNotNull(registeredUser);
        assertEquals(testUser.getUsername(), registeredUser.getUsername());
    }

    @Test
    public void testLogin_SuccessfulLogin() {
        // Arrange: Mock the repository to return a user with the correct password
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);

        // Act: Call the login method
        UserEntity loggedInUser = employeeService.login(testUser);

        // Assert: Ensure the user is logged in successfully
        assertNotNull(loggedInUser);
        assertEquals(testUser.getUsername(), loggedInUser.getUsername());
    }

    @Test
    public void testLogin_InvalidUsernameOrPassword() {
        // Arrange: Mock the repository to return null (user not found)
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(null);

        // Act & Assert: Ensure IllegalArgumentException is thrown
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.login(testUser);
        });
        assertEquals("Invalid username or password!", thrown.getMessage());
    }
}
