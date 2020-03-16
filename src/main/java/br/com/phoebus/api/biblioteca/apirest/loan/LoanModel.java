package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.book.BookModel;
import br.com.phoebus.api.biblioteca.apirest.user.UserModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="loan")
public class LoanModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id")
    private long id;

    private String loanTime;

    @OneToOne
    private UserModel user;

    @OneToMany
    private List<BookModel> books;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getUser(){
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

}