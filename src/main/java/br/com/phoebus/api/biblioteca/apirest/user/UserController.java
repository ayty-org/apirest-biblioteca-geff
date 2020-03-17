package br.com.phoebus.api.biblioteca.apirest.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserModel>> listUsers(){
        List<UserModel> users = userRepository.findAll();

        return ResponseEntity.ok().body(users);
    }

    // Retornar um ResponseEntity e dar um notFound caso userFound seja null
    // ok
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable(value = "id") long id){
        UserModel userFound = userRepository.findById(id);
        if (userFound == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userFound);
    }

    //retornar um ResponseEntity
    //ok
    @PostMapping
    public ResponseEntity<UserModel> postUser(@RequestBody UserModel newUser){
        userRepository.save(newUser);
        return ResponseEntity.ok().body(newUser);
    }

    //retornar um ResponseEntity
    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody UserModel deleteUser){
        UserModel delUser = userRepository.findById(deleteUser.getId()); //Não achei um metodo pronto que procurasse o User pelo User passado como parametro
        if (delUser == deleteUser){
            userRepository.delete(deleteUser);
            return ResponseEntity.ok().build(); //Devo usar o build()??
        }
        return ResponseEntity.badRequest().build(); //Aqui tbm?
    }
    //retornar um ResponseEntity
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable(value = "id") long id){
        UserModel delUser = userRepository.findById(id);
        if (delUser == null){
            return ResponseEntity.badRequest().build(); //Aqui tbm?
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build(); //Aqui tbm?

    }
    //retornar um ResponseEntity
    @PutMapping
    public ResponseEntity<UserModel> putUser(@RequestBody UserModel attUser){
        UserModel user = userRepository.findById(attUser.getId());
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        userRepository.save(attUser);
        return ResponseEntity.ok().body(attUser);
    }
}
