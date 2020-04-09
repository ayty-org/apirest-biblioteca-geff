package br.com.phoebus.api.biblioteca.apirest.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Long id;

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

    public static BookDTO from(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .resume(book.getResume())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .year(book.getYear())
                .build();
    }
}
