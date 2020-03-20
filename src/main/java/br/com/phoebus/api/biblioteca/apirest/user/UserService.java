package br.com.phoebus.api.biblioteca.apirest.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel findUserById(long id){
        return repository.findById(id);
    }

    public List<UserModel> findUsers(){
        return repository.findAll();
    }

    public UserModel updateUser(UserModel attUser){
        repository.save(attUser);
        return attUser;
    }

    public void deleteUser(UserModel delUser){
        repository.delete(delUser);
    }

    public void deleteUserById(long id){
        repository.deleteById(id);
    }

}
