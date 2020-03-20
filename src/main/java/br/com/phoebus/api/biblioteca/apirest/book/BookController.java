package br.com.phoebus.api.biblioteca.apirest.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping
    public ResponseEntity<List<BookModel>> listBooks(){
        List<BookModel> books = service.findBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{id}")
    ResponseEntity<BookModel> getBook(@PathVariable(value = "id") long id){
        BookModel bookFound = service.findBookById(id);
        if (bookFound == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookFound);
    }

    @PostMapping
    ResponseEntity<BookModel> postBook(@RequestBody BookModel newBook){
        service.createBook(newBook);
        return ResponseEntity.ok().body(newBook);
    }

    //O Metodo Delete só existe ser for pelo ID ou pode ser sem ser pelo ID? como esse a baixo
    @DeleteMapping //Este metodo não está funcionando como deveria
    ResponseEntity deleteBook(@RequestBody BookModel deleteBook){
        BookModel delBook = service.findBookById(deleteBook.getId());
        if (delBook == deleteBook){ // Essa condição não está sendo satisfeita, procurar uma solução
            service.deleteBook(deleteBook);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteBookById(@PathVariable(value = "id") long id){
        BookModel delBook = service.findBookById(id);
        if (delBook == null){
            return ResponseEntity.badRequest().build();
        }
        service.deleteBookById(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    ResponseEntity<BookModel> putBook(@RequestBody BookModel attBook){
        BookModel book = service.findBookById(attBook.getId());
        if (book == null){
            return ResponseEntity.badRequest().build();
        }
        service.updateBook(attBook);
        return ResponseEntity.ok().body(attBook);
    }
}
