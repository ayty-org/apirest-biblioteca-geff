package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllUsersImpl implements GetAllUsers{

    private UserRepository repository;
    @Override
    public List<UserApp> getAllUsers() {
        return repository.findAll();
    }
}
