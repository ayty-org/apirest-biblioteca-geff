package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.book.services.SaveBookImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookDTOBuilder.createBookDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida funcionalidade do serviço responsável por criar um livro")
public class BookSaveTest {

    @Mock
    private BookRepository repository;

    private SaveBookImpl saveBook;

    @BeforeEach
    public void setUp() {
        this.saveBook = new SaveBookImpl(repository);
    }

    @Test
    @DisplayName("Deve cruar um livro")
    void shouldCreateBook() {

        //BookDTO que vem do request
        BookDTO book = createBookDTO().build();
        //Respositorio chamando o metodo save com o DTO
        saveBook.save(book);

        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(repository).save(captorBook.capture());

        Book result = captorBook.getValue();

        //Verificação
        assertAll("Book",
                () -> assertThat(result.getAuthor(), is(book.getAuthor())),
                () -> assertThat(result.getIsbn(), is(book.getIsbn())),
                () -> assertThat(result.getResume(), is(book.getResume())),
                () -> assertThat(result.getTitle(), is(book.getTitle())),
                () -> assertThat(result.getYear(), is(book.getYear()))
        );
    }
}
