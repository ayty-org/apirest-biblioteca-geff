package br.com.phoebus.api.biblioteca.apirest.book;


import br.com.phoebus.api.biblioteca.apirest.book.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final SaveBook saveBook;
    private final DeleteBook deleteBook;
    private final ListBooks listBooks;
    private final GetBook getBook;
    private final EditBook editBook;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postBook(@Valid @RequestBody BookDTO newBookDTO){
        saveBook.save(newBookDTO);
    }

    //Falar com kawe sobre o metodo delete do repository não ter orElseTrow
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBookById(@PathVariable(value = "id") long id){
        deleteBook.delete(id);
    }

    @GetMapping
    List<BookDTO> listBooks(){
        return listBooks.listBooks();
    }

    @GetMapping("/{id}")
    BookDTO getBook(@PathVariable(value = "id") long id){
        return getBook.find(id);
    }


    //Verificar se estou usando certo o DTO aqui
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putBook(@Validated @PathVariable(value = "id")long id, @RequestBody BookDTO attBook){
        editBook.edit(id, attBook);
    }
}
