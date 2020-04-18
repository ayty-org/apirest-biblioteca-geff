package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListPageUsersServiceImpl implements ListPageUsersService {

    private final UserRepository repository;

    @Override
    public Page<UserAppDTO> ListUserOnPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "id");
        return UserAppDTO.from(repository.findAll(pageRequest));
    }
}
