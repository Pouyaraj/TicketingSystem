package com.example.TicketingSystem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.TicketingSystem.entity.AccountEntity;
import com.example.TicketingSystem.repository.AccountRepository;
import com.example.TicketingSystem.repository.TicketRepository;
import com.example.TicketingSystem.service.AccountService;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TicketRepository ticketRepository;

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountService(ticketRepository, accountRepository);
    }

    @Test
    void testRegister_whenUsernameAlreadyExists_shouldThrowException() {
        AccountEntity user = new AccountEntity();
        user.setUsername("existingUser");
        when(accountRepository.findByUsername("existingUser")).thenReturn(new AccountEntity());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            accountService.register(user);
        });

        assertEquals("Username already exists. Please choose a different username.", thrown.getMessage());
    }

    @Test
    void testLogin_whenInvalidCredentials_shouldThrowException() {
        AccountEntity user = new AccountEntity();
        user.setUsername("user");
        user.setPassword("password");

        when(accountRepository.findByUsername("user")).thenReturn(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            accountService.login(user);
        });

        assertEquals("Invalid username or password!", thrown.getMessage());
    }
}
