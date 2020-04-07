package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.book.BookRepository;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetBookImpl implements GetBook {

    private final BookRepository repository;

    @Override
    public BookDTO find(Long id) {
        return BookDTO.from(repository.findById(id).orElseThrow(BookNotFoundException::new));
    }
}
