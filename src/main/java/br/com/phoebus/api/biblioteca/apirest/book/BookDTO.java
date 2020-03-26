package br.com.phoebus.api.biblioteca.apirest.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder(builderClassName = "Builder")
public class BookDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String title;

    private String resume;

    private String isbn;

    private String author;

    private String year;

    /*public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.resume = book.getResume();
        this.isbn = book.getIsbn();
        this.author = book.getAuthor();
    }*/

    public static BookDTO from(Book book){
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .resume(book.getResume())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .year(book.getYear())
                .build();
    }

    public static Book to(BookDTO bookDTO){
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .resume(bookDTO.getResume())
                .isbn(bookDTO.getIsbn())
                .author(bookDTO.getAuthor())
                .year(bookDTO.getYear())
                .build();
    }
}
