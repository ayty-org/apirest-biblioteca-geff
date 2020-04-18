package br.com.phoebus.api.biblioteca.apirest.book.services;


import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService {

    private final BookRepository repository;

    @Override
    public void save(BookDTO newBookDTO) {
        repository.save(Book.to(newBookDTO));
    }
}
