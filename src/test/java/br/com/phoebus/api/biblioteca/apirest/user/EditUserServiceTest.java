package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserInconsistencyInDataException;
import br.com.phoebus.api.biblioteca.apirest.user.service.EditUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppBuilder.createUserApp;
import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppDTOBuilder.createUserAppDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço responsável por editar um usuário")
public class EditUserServiceTest {

    @Mock
    private UserRepository repository;

    private EditUserServiceImpl editUser;

    @BeforeEach
    void setUp() {
        this.editUser = new EditUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    void shouldEditUser() {
        UserAppDTO userAppDTO = createUserAppDTO()
                .name("Name Edit")
                .telephone("22 2 222222222").build();

        UserApp userApp = createUserApp().build();
        Optional<UserApp> userAppOptional = Optional.of(userApp);
        when(repository.findById(anyLong())).thenReturn(userAppOptional);

        editUser.edit(1L, userAppDTO);

        ArgumentCaptor<UserApp> captorUser = ArgumentCaptor.forClass(UserApp.class);
        verify(repository).save(captorUser.capture());

        UserApp result = captorUser.getValue();

        assertAll("User",
                () -> assertThat(result.getAge(), is(userAppDTO.getAge())),
                () -> assertThat(result.getName(), is(userAppDTO.getName())),
                () -> assertThat(result.getTelephone(), is(userAppDTO.getTelephone()))
        );
    }

    @Test
    @DisplayName("Deve lançar uma exceção")
    void shouldUserIncosistencyInDataException() {
        UserAppDTO userAppDTO = createUserAppDTO()
                .name("Name Edit")
                .telephone("22 2 222222222").build();

        UserApp userApp = createUserApp().build();
        Optional<UserApp> userAppOptional = Optional.of(userApp);
        when(repository.findById(anyLong())).thenThrow(new UserInconsistencyInDataException());

        assertThrows(UserInconsistencyInDataException.class, () -> editUser.edit(1L, userAppDTO));

        verify(repository, times(0)).save(any());
    }

}
