package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.book.BookRepository;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookInconsistencyInDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateBookImpl implements UpdateBook{

    private final BookRepository repository;

    @Override
    public void update(Long id, BookDTO bookDTO) {
        if (id == bookDTO.getId()) {
            Book attBook = BookDTO.to(bookDTO);
            repository.save(attBook);
            return;
        }
        throw new BookInconsistencyInDataException();
    }
}
