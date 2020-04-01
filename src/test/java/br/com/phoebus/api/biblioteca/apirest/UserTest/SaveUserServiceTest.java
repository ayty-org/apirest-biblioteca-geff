package br.com.phoebus.api.biblioteca.apirest.UserTest;

import br.com.phoebus.api.biblioteca.apirest.exceptions.NotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("Service")
@ExtendWith(MockitoExtension.class)
@DisplayName("Valida o serviço de criar um User")
public class CreateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserApp user;

    //private UserService service;

    /*@BeforeEach
    void setUp(){
        this.service = new UserService(userRepository);
    }*/

    @Test
    @DisplayName("Deve criar um user")
    void shouldCreateUser() throws NotFoundException{

    }

}
