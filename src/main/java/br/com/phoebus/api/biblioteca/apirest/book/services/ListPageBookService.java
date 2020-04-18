package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ListPageBookService {

    Page<BookDTO> ListBookOnPage(Integer page, Integer size);
}
