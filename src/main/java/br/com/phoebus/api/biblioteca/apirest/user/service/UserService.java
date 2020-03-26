package br.com.phoebus.api.biblioteca.apirest.user.service;


import br.com.phoebus.api.biblioteca.apirest.exceptions.NotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public UserApp findUserById(long id){
        verifyUserExist(id);
        return repository.findById(id);
    }

    public List<UserApp> findUsers(){
        return repository.findAll();
    }

    public void createUser(UserApp newUser){
        repository.save(newUser);
    }

    public void updateUser(UserApp attUser){
        verifyUserExist(attUser.getId());
        repository.save(attUser);
    }

    /*public void deleteUser(UserApp delUser){
        repository.delete(delUser);
    }*/

    public void deleteUserById(long id){
        verifyUserExist(id);
        repository.deleteById(id);
    }

    private void verifyUserExist(long id){
        if (repository.findById(id) == null){
            throw new NotFoundException("User not found for ID: "+id);
        }
    }

}
