package br.com.phoebus.api.biblioteca.apirest.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModel, Long> {

    BookModel findById(long id);
}
