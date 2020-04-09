package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.service.DeleteUserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade de serviço responsável por deletar um usuário")
public class UserDeleteTest {

    @Mock
    private UserRepository repository;

    private DeleteUserImpl deleteUser;

    @BeforeEach
    void setUp() {
        this.deleteUser = new DeleteUserImpl(repository);
    }

    @Test
    @DisplayName("Deve deletar um usuário")
    void shouldDeleteUser() {

        when(repository.existsById(anyLong())).thenReturn(true);

        deleteUser.delete(1L);

        verify(repository).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve lançar uma exceção user not found exception")
    void shouldNotFoundException() {

        when(repository.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(UserNotFoundException.class, () -> deleteUser.delete(1L));

        verify(repository, times(0)).deleteById(anyLong());
    }
}
