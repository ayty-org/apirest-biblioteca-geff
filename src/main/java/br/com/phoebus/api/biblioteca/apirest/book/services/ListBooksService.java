package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;

import java.util.List;

@FunctionalInterface
public interface ListBooksService {

    List<BookDTO> listBooks();
}
