package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetUserServiceImpl implements GetUserService {
    private final UserRepository repository;

    @Override
    public UserAppDTO getUser(Long id) {
        return UserAppDTO.from(repository.findById(id).orElseThrow(UserNotFoundException::new));
    }
}
