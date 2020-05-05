package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.service.*;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppDTOBuilder.createUserAppDTO;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserControllerV1.class)
@DisplayName("Valida a funcionalidade do controller de User")
public class UserControllerV1Test {

    private final String URL_USER = "/v1/user";

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

    @Test
    @DisplayName("Deleta um usuário")
    void shouldDeleteUserForID() throws Exception {

        mockMvc.perform(delete(URL_USER + "/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteUserService).delete(1L);
    }

    @Test
    @DisplayName("Edita um usuário")
    void shouldEditUser() throws Exception {

        mockMvc.perform(put(URL_USER + "/{id}", 1L)
                .content(readJson("userAppDTOEdit.json")) //Estudar como utilizar outra maneira utilizando o ObjectMapper
                .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isNoContent());

        //verify(editUserService).edit(1L, any(UserAppDTO.class));

    }

    @Test
    @DisplayName("Pesquisa usuário por ID")
    void shouldReturnUserForID() throws Exception {

        UserAppDTO userAppDTO = createUserAppDTO().build();
        when(getUserService.getUser(1L)).thenReturn(userAppDTO);

        mockMvc.perform(get(URL_USER + "/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(userAppDTO.getName())))
                .andExpect(jsonPath("$.age", is(userAppDTO.getAge())))
                .andExpect(jsonPath("$.telephone", is(userAppDTO.getTelephone())));

        verify(getUserService).getUser(1L);
    }

    @Test
    @DisplayName("Pesquisa usuário que não existe e lança exceção")
    void shouldExceptionNotFoundUserForID() throws Exception {

        when(getUserService.getUser(anyLong())).thenThrow( new UserNotFoundException());

        mockMvc.perform(get(URL_USER+"{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    /*@Test
    @DisplayName("Traz a lista de usuários por paginação")
    void shouldListPageUseres() throws Exception {

        UserAppDTO userApp1 = createUserAppDTO().id(1L).name("name user 1").telephone("111111111").build();
        UserAppDTO userApp2 = createUserAppDTO().id(2L).name("name user 2").age(14).build();


        Page<UserAppDTO> userAppDTOPage = new PageImpl<>(Collections.list(Arrays.asList(userApp1, userApp2)));

    }*/

    @Test
    @DisplayName("Traz a lista de usuários")
    void shouldListUsers() throws Exception {

        UserAppDTO userApp1 = createUserAppDTO().id(1L).name("name user 1").telephone("111111111").build();
        UserAppDTO userApp2 = createUserAppDTO().id(2L).name("name user 2").age(14).build();
        UserAppDTO userApp3 = createUserAppDTO().id(3L).name("name user 3").build();
        when(listUsersService.listUsers()).thenReturn(Arrays.asList(userApp1, userApp2, userApp3));

        mockMvc.perform(get(URL_USER)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(3)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].name", is(userApp1.getName())))
                .andExpect(jsonPath("$.[0].age", is(userApp1.getAge())))
                .andExpect(jsonPath("$.[0].telephone", is(userApp1.getTelephone())))
                .andExpect(jsonPath("$.[1].id", is(2)))
                .andExpect(jsonPath("$.[1].name", is(userApp2.getName())))
                .andExpect(jsonPath("$.[1].age", is(userApp2.getAge())))
                .andExpect(jsonPath("$.[1].telephone", is(userApp2.getTelephone())))
                .andExpect(jsonPath("$.[2].id", is(3)))
                .andExpect(jsonPath("$.[2].name", is(userApp3.getName())))
                .andExpect(jsonPath("$.[2].age", is(userApp3.getAge())))
                .andExpect(jsonPath("$.[2].telephone", is(userApp3.getTelephone())));

        verify(listUsersService).listUsers();
    }

    @Test
    @DisplayName("Salva um usuário")
    void shouldSaveUser() throws Exception {

        mockMvc.perform(post(URL_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("UserAppDTO.json"))) //Estudar como utilizar outra maneira utilizando o ObjectMapper
                .andDo(print())
                .andExpect(status().isCreated());

        verify(saveUserService).save(any());

    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/br/com/phoebus/api/biblioteca/apirest/user/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
