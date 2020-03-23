package br.com.phoebus.api.biblioteca.apirest.user;


import br.com.phoebus.api.biblioteca.apirest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserModel> listUsers(){
        return service.findUsers();
    }

    // ok
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserModel getUser(@PathVariable(value = "id") long id){
        return service.findUserById(id);
    }

    //ok
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postUser(@Validated  @RequestBody UserModel newUser){
        service.createUser(newUser);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserById(@PathVariable(value = "id") long id){
        service.deleteUserById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putUser(@Validated @RequestBody UserModel attUser){
        service.updateUser(attUser);
    }

}
