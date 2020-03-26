package br.com.phoebus.api.biblioteca.apirest.book.service;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.book.BookRepository;
import br.com.phoebus.api.biblioteca.apirest.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public Book findBookById(long id){
        verifyBookExist(id);
        return repository.findById(id);
    }

    public List<Book> findBooks(){
        return repository.findAll();
    }

    public Book createBook(Book newBook){
        repository.save(newBook);
        return newBook;
    }

    public Book updateBook(Book attBook){
        verifyBookExist(attBook.getId());
        repository.save(attBook);
        return attBook;
    }
    //Ver ser o livro possui dependencias
    public void deleteBook(Book delBook){
        repository.delete(delBook);
    }
    //Ver ser o livro possui dependencias
    public void deleteBookById(long id){
        verifyBookExist(id);
        repository.deleteById(id);
    }

    private void verifyBookExist(long id){
        if (repository.findById(id) == null){
            throw new NotFoundException("Book not found for ID: "+id);
        }
    }


}
