package br.com.phoebus.api.biblioteca.apirest.book.builders;

import br.com.phoebus.api.biblioteca.apirest.book.Book;

public class BookBuilder {

    public static Book.Builder createBook() {
        return Book.builder()
                .year("2020")
                .author("Gefferson")
                .isbn("2215466-8")
                .resume("Este eh um livro de Gefferson Pires para test")
                .title("Geff Test");
    }
}
