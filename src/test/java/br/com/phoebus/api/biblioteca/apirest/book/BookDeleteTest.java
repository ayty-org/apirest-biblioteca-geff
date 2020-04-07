package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.book.services.DeleteBookImpl;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade de serviço responsavel por deletar um livro")
public class BookDeleteTest {

    @Mock
    private BookRepository repository;

    private DeleteBookImpl deleteBook;

    @BeforeEach
    void setUP() {
        this.deleteBook = new DeleteBookImpl(repository);
    }

    @Test
    @DisplayName("Deve deletar um book")
    void shouldDeleteBook() {

        when(repository.existsById(anyLong())).thenReturn(true);

        deleteBook.delete(1L);

        verify(repository).deleteById(anyLong());

    }

    @Test
    @DisplayName("Deve lançar uma exceção book not found exception")
    void sholdNotFoundException() {

        when(repository.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(BookNotFoundException.class, () -> deleteBook.delete(1L));

        verify(repository, times(0)).deleteById(anyLong());

    }
}
