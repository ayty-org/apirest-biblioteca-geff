package br.com.phoebus.api.biblioteca.apirest.book;


import br.com.phoebus.api.biblioteca.apirest.book.services.EditBookServiceImpl;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookInconsistencyInDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookBuilder.createBook;
import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookDTOBuilder.createBookDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço responsavel por editar um livro")
public class EditBookServiceTest {

    @Mock
    private BookRepository repository;

    private EditBookServiceImpl editBook;

    @BeforeEach
    public void setUp() {
        this.editBook = new EditBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve atualizar um livro")
    void shouldEditBook() {
        BookDTO bookDTO = createBookDTO()
                .author("Author Edit")
                .title("Title Edit")
                .resume("Resume Edit").build();
        Book book = createBook().build();
        Optional<Book> bookOptional = Optional.of(book);
        when(repository.findById(anyLong())).thenReturn(bookOptional);

        editBook.edit(1L, bookDTO);

        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(repository).save(captorBook.capture());

        Book result = captorBook.getValue();

        assertAll("Book",
                () -> assertThat(result.getAuthor(), is(bookDTO.getAuthor())),
                () -> assertThat(result.getIsbn(), is(bookDTO.getIsbn())),
                () -> assertThat(result.getResume(), is(bookDTO.getResume())),
                () -> assertThat(result.getTitle(), is(bookDTO.getTitle())),
                () -> assertThat(result.getYear(), is(bookDTO.getYear()))
        );

    }

    @Test
    @DisplayName("Deve lançar uma exceção")
    void shouldBookIncosistencyInDataException() {
        BookDTO bookDTO = createBookDTO()
                .author("Author Edit")
                .title("Title Edit")
                .resume("Resume Edit").build();
        when(repository.findById(anyLong())).thenThrow(new BookInconsistencyInDataException());

        assertThrows(BookInconsistencyInDataException.class, () -> editBook.edit(1L, bookDTO));
        verify(repository, times(0)).save(any());
    }
}
