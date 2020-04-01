package br.com.phoebus.api.biblioteca.apirest.user;


import br.com.phoebus.api.biblioteca.apirest.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final CreateUser createUser;
    private final DeleteUser deleteUser;
    private final GetAllUsers getAllUsers;
    private final GetUser getUser;
    private final UpdateUser updateUser;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postUser(@Validated  @RequestBody UserApp newUser){
        createUser.create(newUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserById(@PathVariable(value = "id") long id){
        deleteUser.delete(id);
    }


    @GetMapping
    List<UserApp> listUsers(){
        return getAllUsers.getAllUsers();
    }

    @GetMapping("/{id}")
    UserApp getUser(@PathVariable(value = "id") long id){
        return getUser.getUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putUser(@Validated @PathVariable(value = "id") long id, @RequestBody UserAppDTO attUserDTO){
        updateUser.update(id, attUserDTO);
    }

}
