package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;

@FunctionalInterface
public interface EditBook {

    void edit(Long id, BookDTO bookDTO);
}
