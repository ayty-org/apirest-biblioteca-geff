package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteUserImpl implements DeleteUser {

    private final UserRepository repository;

    @Override
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
    }
}
