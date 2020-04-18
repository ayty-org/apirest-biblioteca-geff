package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.UserInconsistencyInDataException;
import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditUserServiceImpl implements EditUserService {

    private final UserRepository repository;

    @Override
    public void edit(Long id, UserAppDTO userAppDTO) {
        UserApp userApp = repository.findById(id).orElseThrow(UserInconsistencyInDataException::new);

        userApp.setAge(userAppDTO.getAge());
        userApp.setName(userAppDTO.getName());
        userApp.setTelephone(userAppDTO.getTelephone());

        repository.save(userApp);
    }
}
