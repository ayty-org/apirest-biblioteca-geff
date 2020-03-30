package br.com.phoebus.api.biblioteca.apirest.book.services;

import br.com.phoebus.api.biblioteca.apirest.book.BookRepository;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteBookImpl implements DeleteBook {

    private final BookRepository repository;

    @Override
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch (BookNotFoundException e){
            throw new BookNotFoundException();
        }
    }
}
