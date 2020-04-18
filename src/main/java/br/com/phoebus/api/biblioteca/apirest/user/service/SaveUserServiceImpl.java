package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.phoebus.api.biblioteca.apirest.user.UserApp.to;

@RequiredArgsConstructor
@Service
public class SaveUserServiceImpl implements SaveUserService {

    private final UserRepository repository;

    @Override
    public void save(UserAppDTO newUserAppDTO) {
        repository.save(to(newUserAppDTO));
    }
}
