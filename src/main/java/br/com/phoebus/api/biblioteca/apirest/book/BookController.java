package br.com.phoebus.api.biblioteca.apirest.book;


import br.com.phoebus.api.biblioteca.apirest.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping
    List<Book> listBooks(){
        return service.findBooks();
    }

    @GetMapping("/{id}")
    Book getBook(@PathVariable(value = "id") long id){
        return service.findBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postBook(@Validated @RequestBody Book newBook){
        service.createBook(newBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBookById(@PathVariable(value = "id") long id){
        service.deleteBookById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putBook(@Validated @PathVariable(value = "id")long id, @RequestBody Book attBook){
        service.updateBook(attBook);
    }
}
