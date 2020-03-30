package br.com.phoebus.api.biblioteca.apirest.book.services;


import br.com.phoebus.api.biblioteca.apirest.book.Book;

@FunctionalInterface
public interface CreateBook {

    void create(Book newBook);
}
