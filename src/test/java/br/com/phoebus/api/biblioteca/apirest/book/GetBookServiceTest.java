package br.com.phoebus.api.biblioteca.apirest.book;


import br.com.phoebus.api.biblioteca.apirest.book.services.GetBookServiceImpl;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço responsavel por buscar um livro")
public class GetBookServiceTest {

    @Mock
    private BookRepository repository;

    private GetBookServiceImpl getBook;

    @BeforeEach
    public void setUp() {

        this.getBook = new GetBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve retornar um livro")
    void shouldFindBook() {

        Book book = createBook().build();
        Optional<Book> bookOptional = Optional.of(book);
        when(repository.findById(anyLong())).thenReturn(bookOptional);

        BookDTO result = this.getBook.find(1L);

        //verificação
        assertAll("book",
                () -> assertThat(result.getAuthor(), is(book.getAuthor())),
                () -> assertThat(result.getIsbn(), is(book.getIsbn())),
                () -> assertThat(result.getResume(), is(book.getResume())),
                () -> assertThat(result.getTitle(), is(book.getTitle())),
                () -> assertThat(result.getYear(), is(book.getYear()))
        );
    }

    @Test
    @DisplayName("Deve lançar uma exceção")
    void shouldNotFoundBookException() throws BookNotFoundException {

        when(repository.findById(anyLong())).thenThrow(new BookNotFoundException());

        assertThrows(BookNotFoundException.class, () -> getBook.find(1L));
        // apenas para testar se foi chamado
        // verify(repository, times(1)).findById(1L);
    }
}
