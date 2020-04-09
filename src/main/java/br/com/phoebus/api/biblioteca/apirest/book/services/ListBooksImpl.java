package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ListBooksImpl implements ListBooks {

    private final BookRepository repository;

    @Override
    public List<BookDTO> listBooks() {
        List<Book> books = repository.findAll();
        List<BookDTO> booksDTO = new ArrayList<BookDTO>() {
        };
        for (Book book : books) {
            booksDTO.add(BookDTO.from(book));
        }
        return booksDTO;
    }
}
