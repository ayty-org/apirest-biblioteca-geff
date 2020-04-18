package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.user.service.ListPageUsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;

import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppBuilder.createUserApp;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço em listar os usuarios por paginas")
public class ListPageUserServiceTest {

    @Mock
    private UserRepository repository;

    private ListPageUsersServiceImpl listPageUsers;

    @BeforeEach
    void setUp() {
        this.listPageUsers = new ListPageUsersServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve retornar os usuarios por paginação")
    void shouldFindUserPerPage() {

        Pageable pageRequest = PageRequest.of(0, 2,
                Sort.Direction.ASC, "id");
        UserApp userApp = createUserApp().build();
        when(repository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, userApp)));

        Page<UserAppDTO> result = listPageUsers.ListUserOnPage(0, 2);
        assertAll("Books",
                () -> assertThat(result.getNumber(), is(0)),
                () -> assertThat(result.getSize(), is(2)),

                () -> assertThat(result.getContent().get(0).getTelephone(), is(userApp.getTelephone())),
                () -> assertThat(result.getContent().get(0).getName(), is(userApp.getName())),
                () -> assertThat(result.getContent().get(0).getAge(), is(userApp.getAge())),

                () -> assertThat(result.getContent().get(1).getTelephone(), is(userApp.getTelephone())),
                () -> assertThat(result.getContent().get(1).getAge(), is(userApp.getAge())),
                () -> assertThat(result.getContent().get(1).getName(), is(userApp.getName()))

        );

    }
}
