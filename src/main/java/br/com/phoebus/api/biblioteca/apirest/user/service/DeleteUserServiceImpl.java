package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteUserServiceImpl implements DeleteUserService {

    private final UserRepository repository;

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException();
        }
        repository.deleteById(id);
    }
}
