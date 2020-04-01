package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserApp;

@FunctionalInterface
public interface GetUser {

    UserApp getUser(Long id);
}
