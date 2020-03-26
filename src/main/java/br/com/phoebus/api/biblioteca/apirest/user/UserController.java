package br.com.phoebus.api.biblioteca.apirest.user;


import br.com.phoebus.api.biblioteca.apirest.user.service.UserService;
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

    @Autowired
    private UserService service;

    @GetMapping
    List<UserApp> listUsers(){
        return service.findUsers();
    }

    // ok
    @GetMapping("/{id}")
    UserApp getUser(@PathVariable(value = "id") long id){
        return service.findUserById(id);
    }

    //ok
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postUser(@Validated  @RequestBody UserApp newUser){
        service.createUser(newUser);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserById(@PathVariable(value = "id") long id){
        service.deleteUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putUser(@Validated @PathVariable(value = "id") long id, @RequestBody UserApp attUser){
        service.updateUser(attUser);
    }

}
