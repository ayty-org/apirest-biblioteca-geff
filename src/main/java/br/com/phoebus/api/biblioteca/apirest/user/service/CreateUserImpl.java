package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateUserImpl implements CreateUser {

    private final UserRepository repository;

    @Override
    public void create(UserApp newUserApp) {
        repository.save(newUserApp);
    }
}
