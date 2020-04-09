package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.user.service.ListUsersImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppBuilder.createUserApp;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço em listar os usuários")
public class UserListTest {

    @Mock
    private UserRepository repository;

    private ListUsersImpl listUsers;

    @BeforeEach
    void setUp() {
        this.listUsers = new ListUsersImpl(repository);
    }

    @Test
    @DisplayName("deve listar os usuários")
    void shouldListUsers() {
        UserApp userApp1 = createUserApp().name("name user 1").telephone("111111111").build();
        UserApp userApp2 = createUserApp().name("name user 2").age(14).build();
        UserApp userApp3 = createUserApp().name("name user 3").build();
        when(repository.findAll()).thenReturn(Arrays.asList(userApp1, userApp2, userApp3));

        List<UserAppDTO> result = this.listUsers.listUsers();

        assertAll("books",
                () -> assertThat(result.size(), is(3)),/*Não gosto desse numero magico, achar um jeito de deixar variavel*/
                () -> assertThat(result.get(0).getName(), is(userApp1.getName())),
                () -> assertThat(result.get(0).getTelephone(), is(userApp1.getTelephone())),
                () -> assertThat(result.get(0).getAge(), is(userApp1.getAge())),

                () -> assertThat(result.get(1).getName(), is(userApp2.getName())),
                () -> assertThat(result.get(1).getTelephone(), is(userApp2.getTelephone())),
                () -> assertThat(result.get(1).getAge(), is(userApp2.getAge())),

                () -> assertThat(result.get(2).getName(), is(userApp3.getName())),
                () -> assertThat(result.get(2).getTelephone(), is(userApp3.getTelephone())),
                () -> assertThat(result.get(2).getAge(), is(userApp3.getAge()))

        );
    }
}
