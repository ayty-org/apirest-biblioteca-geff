package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;

@FunctionalInterface
public interface EditUserService {

    void edit(Long id, UserAppDTO userAppDTO);
}
