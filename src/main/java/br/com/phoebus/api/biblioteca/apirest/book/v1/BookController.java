package br.com.phoebus.api.biblioteca.apirest.book.v1;


import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.book.services.DeleteBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.EditBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.GetBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.ListBooksService;
import br.com.phoebus.api.biblioteca.apirest.book.services.SaveBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/book")
public class BookController {

    private final SaveBookService saveBookService;
    private final DeleteBookService deleteBookService;
    private final ListBooksService listBooksService;
    private final GetBookService getBookService;
    private final EditBookService editBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postBook(@Valid @RequestBody BookDTO newBookDTO) {
        saveBookService.save(newBookDTO);
    }

    //Falar com kawe sobre o metodo delete do repository não ter orElseTrow
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBookById(@PathVariable(value = "id") long id) {
        deleteBookService.delete(id);
    }

    @GetMapping
    List<BookDTO> listBooks() {
        return listBooksService.listBooks();
    }

    @GetMapping("/{id}")
    BookDTO getBook(@PathVariable(value = "id") long id) {
        return getBookService.find(id);
    }

    //Verificar se estou usando certo o DTO aqui
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putBook(@Valid @PathVariable(value = "id") long id, @RequestBody BookDTO attBook) {
        editBookService.edit(id, attBook);
    }
}
