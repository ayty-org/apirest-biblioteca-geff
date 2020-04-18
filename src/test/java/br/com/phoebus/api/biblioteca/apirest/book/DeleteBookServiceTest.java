package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.book.services.DeleteBookServiceImpl;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade de serviço responsavel por deletar um livro")
public class DeleteBookServiceTest {

    @Mock
    private BookRepository repository;

    private DeleteBookServiceImpl deleteBook;

    @BeforeEach
    void setUP() {
        this.deleteBook = new DeleteBookServiceImpl(repository);
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
