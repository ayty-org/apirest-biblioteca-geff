package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="loan")
@Builder(builderClassName = "Builder")
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id")
    private long id;

    private String loanTime;

    @OneToOne
    private UserApp user;

    @OneToMany
    private List<Book> books;

}
