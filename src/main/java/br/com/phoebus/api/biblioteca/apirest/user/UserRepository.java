package br.com.phoebus.api.biblioteca.apirest.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findById(long id);
}
