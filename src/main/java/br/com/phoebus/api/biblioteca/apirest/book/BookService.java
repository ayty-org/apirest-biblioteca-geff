package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public BookModel findBookById(long id){
        verifyBookExist(id);
        return repository.findById(id);
    }

    public List<BookModel> findBooks(){
        return repository.findAll();
    }

    public BookModel createBook(BookModel newBook){
        repository.save(newBook);
        return newBook;
    }

    public BookModel updateBook(BookModel attBook){
        verifyBookExist(attBook.getId());
        repository.save(attBook);
        return attBook;
    }
    //Ver ser o livro possui dependencias
    public void deleteBook(BookModel delBook){
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
