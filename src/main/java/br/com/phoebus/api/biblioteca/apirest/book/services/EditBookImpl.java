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
        Book book = repository.findById(id).orElseThrow(BookInconsistencyInDataException::new);

        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setResume(bookDTO.getResume());
        book.setTitle(bookDTO.getTitle());
        book.setYear(bookDTO.getYear());

        repository.save(book);
    }
}
