package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserNotFoundException;
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
        UserApp userApp = repository.findById(id).orElseThrow(UserNotFoundException::new);

        userApp.setAge(userAppDTO.getAge());
        userApp.setName(userAppDTO.getName());
        userApp.setTelephone(userAppDTO.getTelephone());

        repository.save(userApp);
    }
}
