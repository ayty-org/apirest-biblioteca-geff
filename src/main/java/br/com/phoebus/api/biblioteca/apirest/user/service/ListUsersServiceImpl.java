package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListUsersServiceImpl implements ListUsersService {

    private final UserRepository repository;

    @Override
    public List<UserAppDTO> listUsers() {
        List<UserApp> users = repository.findAll();
        return UserAppDTO.from(users);
    }
}
