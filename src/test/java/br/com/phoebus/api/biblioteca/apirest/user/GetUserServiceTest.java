package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.service.GetUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppBuilder.createUserApp;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço responsável por buscar um usuário")
public class GetUserServiceTest {

    @Mock
    private UserRepository repository;

    private GetUserServiceImpl getUser;

    @BeforeEach
    void setUp() {
        this.getUser = new GetUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve buscar um usuário")
    void shouldGetUser() {

        UserApp userApp = createUserApp().build();
        Optional<UserApp> userAppOptional = Optional.of(userApp);
        when(repository.findById(anyLong())).thenReturn(userAppOptional);

        UserAppDTO result = this.getUser.getUser(1L);

        assertAll("User",
                () -> assertThat(result.getAge(), is(userApp.getAge())),
                () -> assertThat(result.getName(), is(userApp.getName())),
                () -> assertThat(result.getTelephone(), is(userApp.getTelephone()))
        );
    }

    @Test
    @DisplayName("Deve lançar uma exceção")
    void shouldNotFoundUserException() {

        when(repository.findById(anyLong())).thenThrow(new UserNotFoundException());

        assertThrows(UserNotFoundException.class, () -> getUser.getUser(1L));
        verify(repository, times(1)).findById(anyLong());
    }
}
