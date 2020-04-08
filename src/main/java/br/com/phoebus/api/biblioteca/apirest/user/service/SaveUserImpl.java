package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveUserImpl implements SaveUser {

    private final UserRepository repository;

    @Override
    public void save(UserAppDTO newUserAppDTO) {
        repository.save(UserAppDTO.to(newUserAppDTO));
    }
}