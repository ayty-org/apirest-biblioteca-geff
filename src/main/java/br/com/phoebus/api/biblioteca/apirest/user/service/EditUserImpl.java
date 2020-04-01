package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserInconsistencyInDataException;
import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditUserImpl implements EditUser {

    private final UserRepository repository;

    @Override
    public void edit(Long id, UserAppDTO userAppDTO) {
        if (id == userAppDTO.getId()){
            UserApp attUser = UserAppDTO.to(userAppDTO);
            repository.save(attUser);
            return;
        }
        throw new UserInconsistencyInDataException();
    }
}
