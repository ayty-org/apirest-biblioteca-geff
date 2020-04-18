package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.book.services.ListBooksServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida funcionalidade do serciço responsável por listar todos os livros")
public class ListBookServiceTest {

    @Mock
    private BookRepository repository;

    private ListBooksServiceImpl listBooks;

    @BeforeEach
    public void setUp() {
        this.listBooks = new ListBooksServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve listar todos os livros")
    void shouldListBooks() {
        Book book1 = createBook().title("Title List do Book 1").build();
        Book book2 = createBook().resume("Resume List do Book2").build();
        Book book3 = createBook().author("Author List do Book3").build();
        when(repository.findAll()).thenReturn(Arrays.asList(book1, book2, book3));


        List<BookDTO> result = this.listBooks.listBooks();

        assertAll("books",
                () -> assertThat(result.size(), is(3)),/*Não gosto desse numero magico, achar um jeito de deixar variavel*/
                () -> assertThat(result.get(0).getAuthor(), is(book1.getAuthor())),
                () -> assertThat(result.get(0).getIsbn(), is(book1.getIsbn())),
                () -> assertThat(result.get(0).getResume(), is(book1.getResume())),
                () -> assertThat(result.get(0).getTitle(), is(book1.getTitle())),

                () -> assertThat(result.get(1).getResume(), is(book2.getResume())),
                () -> assertThat(result.get(1).getTitle(), is(book2.getTitle())),

                () -> assertThat(result.get(2).getAuthor(), is(book3.getAuthor())),
                () -> assertThat(result.get(2).getResume(), is(book3.getResume()))
        );
    }
}
