package br.com.phoebus.api.biblioteca.apirest.user.service;


import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;

@FunctionalInterface
public interface SaveUserService {

    void save(UserAppDTO userAppDTO);
}
