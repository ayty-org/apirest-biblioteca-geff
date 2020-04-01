package br.com.phoebus.api.biblioteca.apirest.user.service;


import br.com.phoebus.api.biblioteca.apirest.user.UserApp;

@FunctionalInterface
public interface CreateUser {

    void create(UserApp userApp);
}
