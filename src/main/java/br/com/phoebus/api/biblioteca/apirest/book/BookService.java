package br.com.phoebus.api.biblioteca.apirest.book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    public BookModel findBookById(long id){
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
        repository.save(attBook);
        return attBook;
    }
    //Ver ser o livro possui dependencias
    public void deleteBook(BookModel delBook){
        repository.delete(delBook);
    }
    //Ver ser o livro possui dependencias
    public void deleteBookById(long id){
        repository.deleteById(id);
    }


}
