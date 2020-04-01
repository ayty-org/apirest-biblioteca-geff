package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="loan")
@Builder(builderClassName = "Builder")
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id")
    private long id;

    private String loanTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp user;

    @ManyToMany
    @JoinTable(name = "lend_book",
            joinColumns = @JoinColumn(name = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    Set<Book> booksLends;

}
