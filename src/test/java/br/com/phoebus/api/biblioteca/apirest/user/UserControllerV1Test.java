package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.user.service.DeleteUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.EditUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.GetUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.ListPageUsersService;
import br.com.phoebus.api.biblioteca.apirest.user.service.ListUsersService;
import br.com.phoebus.api.biblioteca.apirest.user.service.SaveUserService;
import br.com.phoebus.api.biblioteca.apirest.user.v1.UserControllerV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppDTOBuilder.createUserAppDTO;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserControllerV1.class)
@DisplayName("Valida a funcionalidade do controller de User")
public class UserControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeleteUserService deleteUserService;
    @MockBean
    private EditUserService editUserService;
    @MockBean
    private GetUserService getUserService;
    @MockBean
    private ListPageUsersService listPageUsersService;
    @MockBean
    private ListUsersService listUsersService;
    @MockBean
    private SaveUserService saveUserService;

    public UserControllerV1Test() {
    }

    @Test
    @DisplayName("Pesquisa usuário por ID")
    void shouldReturnUserForID() throws Exception {

        UserAppDTO userAppDTO = createUserAppDTO().build();
        when(getUserService.getUser(1L)).thenReturn(userAppDTO);

        mockMvc.perform(get("/v1/user/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userAppDTO.getId())))
                .andExpect(jsonPath("$.name", is(userAppDTO.getName())))
                .andExpect(jsonPath("$,age", is(userAppDTO.getAge())))
                .andExpect(jsonPath("$.telephone", is(userAppDTO.getTelephone())));

    }
}
