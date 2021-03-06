package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
@Builder(builderClassName = "Builder")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Long id;

    private String title;

    private String resume;

    private String isbn;

    private String author;

    private String year;

    @ManyToMany(mappedBy = "booksLends")
    private List<Loan> lend = new ArrayList<>();
    //private Set<Loan> lend = new HashSet<>();

    public static Book to(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .resume(bookDTO.getResume())
                .isbn(bookDTO.getIsbn())
                .author(bookDTO.getAuthor())
                .year(bookDTO.getYear())
                .build();
    }

    public static List<Book> to(List<BookDTO> booksDTO) {
        List<Book> books = new ArrayList<>();
        for (BookDTO bookDTO : booksDTO) {
            books.add(Book.to(bookDTO));
        }
        return books;
    }

    public static Page<Book> to(Page<BookDTO> pages) {
        return pages.map(Book::to);
    }
}
