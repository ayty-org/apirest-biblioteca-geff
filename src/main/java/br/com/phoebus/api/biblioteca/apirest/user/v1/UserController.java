package br.com.phoebus.api.biblioteca.apirest.user.v1;


import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    void postUser(@Valid  @RequestBody UserAppDTO newUserDTO){
        saveUser.save(newUserDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserById(@PathVariable(value = "id") long id){
        deleteUser.delete(id);
    }


    @GetMapping
    List<UserAppDTO> listUsers(){
        return listUsers.listUsers();
    }

    @GetMapping("/{id}")
    UserAppDTO getUser(@PathVariable(value = "id") long id){
        return getUser.getUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putUser(@Valid @PathVariable(value = "id") long id, @RequestBody UserAppDTO attUserDTO){
        editUser.edit(id, attUserDTO);
    }

}
