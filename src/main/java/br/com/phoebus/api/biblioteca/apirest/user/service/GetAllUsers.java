package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserApp;

import java.util.List;

@FunctionalInterface
public interface GetAllUsers {

    List<UserApp> getAllUsers();
}
