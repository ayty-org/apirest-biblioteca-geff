package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListBooksServiceImpl implements ListBooksService {

    private final BookRepository repository;

    @Override
    public List<BookDTO> listBooks() {
        List<Book> books = repository.findAll();
        return BookDTO.from(books);
    }
}
