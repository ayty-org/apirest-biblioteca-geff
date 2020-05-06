package br.com.phoebus.api.biblioteca.apirest.loan.builders;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.loan.Loan;

import java.util.ArrayList;
import java.util.List;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookBuilder.createBook;
import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppBuilder.createUserApp;

public class LoanBuilder {

    public static Loan.Builder createLoan() {
        return Loan.builder()
                .loanTime("10/04/2020")
                .userApp(createUserApp()
                        .name("User Loan Test")
                        .build())
                .booksLends(books());
    }

    public static List<Book> books() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(createBook().id(1L).author("Author Loan Test").build());
        books.add(createBook().id(2L).title("Title Loan Test").build());
        return books;
    }
}
