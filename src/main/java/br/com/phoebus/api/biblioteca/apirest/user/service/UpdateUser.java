package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;

@FunctionalInterface
public interface UpdateUser {

    void update(Long id, UserAppDTO userAppDTO);
}
