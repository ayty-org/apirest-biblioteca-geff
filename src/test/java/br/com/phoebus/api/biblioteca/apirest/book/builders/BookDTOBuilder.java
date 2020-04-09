package br.com.phoebus.api.biblioteca.apirest.book.builders;

import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;

public class BookDTOBuilder {

    public static BookDTO.Builder createBookDTO() {
        return BookDTO.builder()
                .year("2020")
                .author("Gefferson")
                .isbn("2215466-8")
                .resume("Este é um livro de Gefferson Pires para test")
                .title("Geff Test");
    }
}
