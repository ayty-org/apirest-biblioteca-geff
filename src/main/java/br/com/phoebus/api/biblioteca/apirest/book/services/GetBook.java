package br.com.phoebus.api.biblioteca.apirest.book.services;


import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;

@FunctionalInterface
public interface GetBook {

    BookDTO find(Long id);
}