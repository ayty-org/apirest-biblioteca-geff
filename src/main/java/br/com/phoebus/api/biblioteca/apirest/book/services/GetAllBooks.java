package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.Book;

import java.util.List;

@FunctionalInterface
public interface GetAllBooks {

    List<Book> getAllBooks();
}
