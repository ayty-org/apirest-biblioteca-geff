package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.service.DeleteUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.EditUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.GetUserService;
import br.com.phoebus.api.biblioteca.apirest.user.service.ListPageUsersService;
import br.com.phoebus.api.biblioteca.apirest.user.service.ListUsersService;
import br.com.phoebus.api.biblioteca.apirest.user.service.SaveUserService;
import br.com.phoebus.api.biblioteca.apirest.user.v1.UserControllerV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppDTOBuilder.createUserAppDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserControllerV1.class)
@DisplayName("Valida a funcionalidade do controller de User")
public class UserControllerV1Test {

    private final String URL_USER = "/v1/user";
    private final String CONT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

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
                .contentType(CONT_TYPE)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteUserService).delete(1L);
    }

    @Test
    @DisplayName("Edita um usuário")
    void shouldEditUser() throws Exception {

        Long id = 1L;
        UserAppDTO userAppDTO = createUserAppDTO().id(id).build();
        userAppDTO.setAge(10);

        mockMvc.perform(put(URL_USER + "/{id}", 1L)
                .contentType(CONT_TYPE)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(userAppDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());

        ArgumentCaptor<UserAppDTO> captorUserDTO = ArgumentCaptor.forClass(UserAppDTO.class);
        ArgumentCaptor<Long> captorLong = ArgumentCaptor.forClass(Long.class);
        verify(editUserService).edit(captorLong.capture(), captorUserDTO.capture());
        UserAppDTO result = captorUserDTO.getValue();

        assertThat(captorLong.getValue()).isEqualTo(id);
        assertThat(result.getId()).isEqualTo(userAppDTO.getId());
        assertThat(result.getTelephone()).isEqualTo(userAppDTO.getTelephone());
        assertThat(result.getName()).isEqualTo(userAppDTO.getName());
        assertThat(result.getAge()).isEqualTo(userAppDTO.getAge());

    }

    @Test
    @DisplayName("Pesquisa usuário por ID")
    void shouldReturnUserForID() throws Exception {

        UserAppDTO userAppDTO = createUserAppDTO().id(1L).build();
        when(getUserService.getUser(1L)).thenReturn(userAppDTO);

        MvcResult mvcResult = mockMvc.perform(get(URL_USER + "/{id}", 1L)
                .contentType(CONT_TYPE)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(userAppDTO))
                .isEqualToIgnoringWhitespace(resultResponseBody);

        verify(getUserService).getUser(1L);
    }

    @Test
    @DisplayName("Pesquisa usuário que não existe e lança exceção")
    void shouldExceptionNotFoundUserForID() throws Exception {

        when(getUserService.getUser(anyLong())).thenThrow(new UserNotFoundException());

        mockMvc.perform(get(URL_USER + "/{id}", 1L)
                .contentType(CONT_TYPE)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getUserService).getUser(1L);
    }

    @Test
    @DisplayName("Traz a lista de usuários por paginação")
    void shouldListPageUseres() throws Exception {

        UserAppDTO userApp1 = createUserAppDTO().id(1L).name("name user 1").telephone("111111111").build();
        UserAppDTO userApp2 = createUserAppDTO().id(2L).name("name user 2").age(14).build();
        UserAppDTO userApp3 = createUserAppDTO().id(3L).name("name user 3").age(15).build();
        UserAppDTO userApp4 = createUserAppDTO().id(4L).name("name user 4").build();
        UserAppDTO userApp5 = createUserAppDTO().id(5L).name("name user 5").build();

        PageRequest pageRequest = PageRequest.of(1, 2);

        List<UserAppDTO> userAppDTOList = Arrays.asList(userApp1,userApp2,userApp3,userApp4,userApp5);
        Page<UserAppDTO> userAppDTOPage = new PageImpl<>(userAppDTOList, pageRequest, userAppDTOList.size());

        when(listPageUsersService.ListUserOnPage(anyInt(),anyInt())).thenReturn(userAppDTOPage);

        MvcResult mvcResult = mockMvc.perform(get(URL_USER)
                .contentType(CONT_TYPE)
                .characterEncoding("utf-8")
                .param("page", "1")
                .param("size", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(userAppDTOPage))
                .isEqualToIgnoringWhitespace(resultResponseBody);
    }

    @Test
    @DisplayName("Traz a lista de usuários")
    void shouldListUsers() throws Exception {

        UserAppDTO userApp1 = createUserAppDTO().id(1L).name("name user 1").telephone("111111111").build();
        UserAppDTO userApp2 = createUserAppDTO().id(2L).name("name user 2").age(14).build();
        UserAppDTO userApp3 = createUserAppDTO().id(3L).name("name user 3").build();

        List<UserAppDTO> userAppDTOList = Arrays.asList(userApp1, userApp2, userApp3);

        when(listUsersService.listUsers()).thenReturn(userAppDTOList);

        MvcResult mvcResult = mockMvc.perform(get(URL_USER)
                .contentType(CONT_TYPE)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(3))).andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(userAppDTOList))
                .isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listUsersService).listUsers();
    }

    @Test
    @DisplayName("Salva um usuário")
    void shouldSaveUser() throws Exception {

        UserAppDTO userAppDTO = createUserAppDTO().id(1L).build();

        mockMvc.perform(post(URL_USER)
                .contentType(CONT_TYPE)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(userAppDTO))) //Estudar como utilizar outra maneira utilizando o ObjectMapper
                .andDo(print())
                .andExpect(status().isCreated());

        ArgumentCaptor<UserAppDTO> captorUser = ArgumentCaptor.forClass(UserAppDTO.class);
        verify(saveUserService).save(captorUser.capture());
        UserAppDTO result = captorUser.getValue();

        assertThat(result.getId()).isEqualTo(userAppDTO.getId());
        assertThat(result.getAge()).isEqualTo(userAppDTO.getAge());
        assertThat(result.getName()).isEqualTo(userAppDTO.getName());
        assertThat(result.getTelephone()).isEqualTo(userAppDTO.getTelephone());

    }
}
