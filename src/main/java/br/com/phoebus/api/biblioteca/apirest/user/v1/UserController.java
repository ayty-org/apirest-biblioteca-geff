package br.com.phoebus.api.biblioteca.apirest.user.v1;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.service.DeleteUser;
import br.com.phoebus.api.biblioteca.apirest.user.service.EditUser;
import br.com.phoebus.api.biblioteca.apirest.user.service.GetUser;
import br.com.phoebus.api.biblioteca.apirest.user.service.ListUsers;
import br.com.phoebus.api.biblioteca.apirest.user.service.SaveUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/user")
public class UserController {

    private final SaveUser saveUser;
    private final DeleteUser deleteUser;
    private final ListUsers listUsers;
    private final GetUser getUser;
    private final EditUser editUser;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postUser(@Valid @RequestBody UserAppDTO newUserDTO) {
        saveUser.save(newUserDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserById(@PathVariable(value = "id") long id) {
        deleteUser.delete(id);
    }


    @GetMapping
    List<UserAppDTO> listUsers() {
        return listUsers.listUsers();
    }

    @GetMapping("/{id}")
    UserAppDTO getUser(@PathVariable(value = "id") long id) {
        return getUser.getUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putUser(@Valid @PathVariable(value = "id") long id, @RequestBody UserAppDTO attUserDTO) {
        editUser.edit(id, attUserDTO);
    }

}
