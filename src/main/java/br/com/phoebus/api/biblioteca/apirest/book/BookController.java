package br.com.phoebus.api.biblioteca.apirest.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<BookModel> listBooks(){
        return service.findBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    BookModel getBook(@PathVariable(value = "id") long id){
        return service.findBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postBook(@Validated @RequestBody BookModel newBook){
        service.createBook(newBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBookById(@PathVariable(value = "id") long id){
        service.deleteBookById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putBook(@Validated @RequestBody BookModel attBook){
        service.updateBook(attBook);
    }
}
