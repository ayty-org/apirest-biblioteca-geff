package br.com.phoebus.api.biblioteca.apirest.user.service;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;

import java.util.List;

@FunctionalInterface
public interface ListUsers {

    List<UserAppDTO> listUsers();
}
