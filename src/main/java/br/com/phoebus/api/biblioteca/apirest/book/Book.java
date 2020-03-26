package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="book")
@Builder(builderClassName = "Builder")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private long id;

    private String title;

    private String resume;

    private String isbn;

    private String author;

    private String year;

    @ManyToOne
    private Loan loan;

    public Book(String title, String resume, String isbn, String author, String year) {
        this.title = title;
        this.resume = resume;
        this.isbn = isbn;
        this.author = author;
        this.year = year;
    }

    public Book(Long id, String title, String resume, String isbn, String author, String year) {
        this.id = id;
        this.title = title;
        this.resume = resume;
        this.isbn = isbn;
        this.author = author;
        this.year = year;
    }
}
