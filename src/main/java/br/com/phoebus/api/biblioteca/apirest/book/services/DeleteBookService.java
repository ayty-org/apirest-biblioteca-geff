package br.com.phoebus.api.biblioteca.apirest.book.services;

@FunctionalInterface
public interface DeleteBookService {

    void delete(Long id);
}
