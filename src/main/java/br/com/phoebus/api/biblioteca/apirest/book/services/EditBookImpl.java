package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.book.BookRepository;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookInconsistencyInDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditBookImpl implements EditBook {

    private final BookRepository repository;

    @Override
    public void edit(Long id, BookDTO bookDTO) {
        if (id == bookDTO.getId()) {
            Book attBook = BookDTO.to(bookDTO);
            repository.save(attBook);
            return;
        }
        throw new BookInconsistencyInDataException();
    }
}
