package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;

@FunctionalInterface
public interface UpdateBook {

    void update(Long id, BookDTO bookDTO);
}
