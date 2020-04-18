package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ListPageUsersService {
    Page<UserAppDTO> ListUserOnPage(Integer page, Integer size);
}
