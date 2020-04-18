package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.user.service.SaveUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;


import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppDTOBuilder.createUserAppDTO;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço referente a salvar um usuário")
public class SaveUserServiceTest {

    @Mock
    private UserRepository repository;

    private SaveUserServiceImpl saveUser;

    @BeforeEach
    void setUp() {
        this.saveUser = new SaveUserServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um usuário")
    void shouldSaveUser() {
        UserAppDTO userAppDTO = createUserAppDTO().build();

        saveUser.save(userAppDTO);

        ArgumentCaptor<UserApp> captorUser = ArgumentCaptor.forClass(UserApp.class);
        verify(repository).save(captorUser.capture());

        UserApp result = captorUser.getValue();

        assertAll("User",
                () -> assertThat(result.getAge(), is(userAppDTO.getAge())),
                () -> assertThat(result.getTelephone(), is(userAppDTO.getTelephone())),
                () -> assertThat(result.getName(), is(userAppDTO.getName()))
        );
    }
}
