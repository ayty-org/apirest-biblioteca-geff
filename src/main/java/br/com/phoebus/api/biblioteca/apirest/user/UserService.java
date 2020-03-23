package br.com.phoebus.api.biblioteca.apirest.user;


import br.com.phoebus.api.biblioteca.apirest.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public UserModel findUserById(long id){
        verifyUserExist(id);
        return repository.findById(id);
    }

    public List<UserModel> findUsers(){
        return repository.findAll();
    }

    public void createUser(UserModel newUser){
        repository.save(newUser);
    }

    public void updateUser(UserModel attUser){
        verifyUserExist(attUser.getId());
        repository.save(attUser);
    }

    /*public void deleteUser(UserModel delUser){
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
