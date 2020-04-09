package br.com.phoebus.api.biblioteca.apirest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserInconsistencyInDataException extends RuntimeException {
    public UserInconsistencyInDataException() {
        super("inconsistency in user data");
    }
}
