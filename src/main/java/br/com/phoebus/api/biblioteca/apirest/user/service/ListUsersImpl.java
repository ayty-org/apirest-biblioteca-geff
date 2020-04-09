package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ListUsersImpl implements ListUsers {

    private final UserRepository repository;

    @Override
    public List<UserAppDTO> listUsers() {
        List<UserApp> users = repository.findAll();
        List<UserAppDTO> usersDTO = new ArrayList<UserAppDTO>() {
        };
        for (UserApp user : users) {
            usersDTO.add(UserAppDTO.from(user));
        }
        return usersDTO;
    }
}
