package br.com.phoebus.api.biblioteca.apirest.book;


import br.com.phoebus.api.biblioteca.apirest.book.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final CreateBook createBook;
    private final DeleteBook deleteBook;
    private final GetAllBooks getAllBooks;
    private final GetBook getBook;
    private final UpdateBook updateBook;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postBook(@Validated @RequestBody Book newBook){
        createBook.create(newBook);
    }

    //Falar com kawe sobre o metodo delete do repository não ter orElseTrow
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBookById(@PathVariable(value = "id") long id){
        deleteBook.delete(id);
    }

    @GetMapping
    List<Book> listBooks(){
        return getAllBooks.getAllBooks();
    }

    @GetMapping("/{id}")
    Book getBook(@PathVariable(value = "id") long id){
        return getBook.find(id);
    }


    //Verificar se estou usando certo o DTO aqui
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putBook(@Validated @PathVariable(value = "id")long id, @RequestBody BookDTO attBook){
        updateBook.update(id, attBook);
    }
}
