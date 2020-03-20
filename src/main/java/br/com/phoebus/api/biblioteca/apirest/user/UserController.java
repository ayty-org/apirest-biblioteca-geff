package br.com.phoebus.api.biblioteca.apirest.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserModel>> listUsers(){
        List<UserModel> users = service.findUsers();

        return ResponseEntity.ok().body(users);
    }

    // Retornar um ResponseEntity e dar um notFound caso userFound seja null
    // ok
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable(value = "id") long id){
        UserModel userFound = service.findUserById(id);
        if (userFound == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userFound);
    }

    //retornar um ResponseEntity
    //ok
    @PostMapping
    public ResponseEntity<UserModel> postUser(@RequestBody UserModel newUser){
        service.createUser(newUser);
        return ResponseEntity.ok().body(newUser);
    }

    //retornar um ResponseEntity
    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody UserModel deleteUser){
        UserModel delUser = service.findUserById(deleteUser.getId()); //Não achei um metodo pronto que procurasse o User pelo User passado como parametro
        if (delUser == deleteUser){
            service.deleteUser(deleteUser);
            return ResponseEntity.ok().build(); //Devo usar o build()??
        }
        return ResponseEntity.badRequest().build(); //Aqui tbm?
    }
    //retornar um ResponseEntity
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable(value = "id") long id){
        UserModel delUser = service.findUserById(id);
        if (delUser == null){
            return ResponseEntity.badRequest().build(); //Aqui tbm?
        }
        service.deleteUserById(id);
        return ResponseEntity.ok().build(); //Aqui tbm?

    }
    //retornar um ResponseEntity
    @PutMapping
    public ResponseEntity<UserModel> putUser(@RequestBody UserModel attUser){
        UserModel user = service.findUserById(attUser.getId());
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        service.updateUser(attUser);
        return ResponseEntity.ok().body(attUser);
    }
}
