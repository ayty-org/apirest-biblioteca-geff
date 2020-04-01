package br.com.phoebus.api.biblioteca.apirest.book;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BookDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotEmpty(message = "Title may not be empty")
    private String title;

    @NotEmpty(message = "Resume may not be empty")
    @Size(max = 500)
    private String resume;

    @NotEmpty(message = "ISBN may not be empty")
    private String isbn;

    @NotEmpty(message = "Author may not be empty")
    private String author;

    @NotEmpty(message = "Year may not be empty")
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
