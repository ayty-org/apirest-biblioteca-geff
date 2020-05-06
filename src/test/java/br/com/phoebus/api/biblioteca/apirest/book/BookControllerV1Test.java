package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.book.services.DeleteBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.EditBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.GetBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.ListBooksService;
import br.com.phoebus.api.biblioteca.apirest.book.services.ListPageBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.SaveBookService;
import br.com.phoebus.api.biblioteca.apirest.book.v1.BookControllerV1;
import br.com.phoebus.api.biblioteca.apirest.exceptions.BookNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookDTOBuilder.createBookDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookControllerV1.class)
@DisplayName("Valida funcionalidade do Controller Book")
public class BookControllerV1Test {

    private final String URI_BOOK = "/v1/book";
    private final String CONT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetBookService getBookService; //ok
    @MockBean
    private ListBooksService listBookService; //ok
    @MockBean
    private ListPageBookService listPageBookService;
    @MockBean
    private SaveBookService saveBookService; //ok
    @MockBean
    private EditBookService updateBookService; //ok
    @MockBean
    private DeleteBookService deleteBookService; //ok

    @Test
    @DisplayName("Pesquisa livro por id")
    void shouldReturnBookForID() throws Exception {

        BookDTO bookDTO = createBookDTO().id(1L).build();

        when(getBookService.find(1L)).thenReturn(bookDTO);

        MvcResult mvcResult = mockMvc.perform(get(URI_BOOK + "/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(bookDTO))
                .isEqualToIgnoringWhitespace(resultResponseBody);

        verify(getBookService).find(1L);
    }

    @Test
    @DisplayName("Pesquisa livro que não existe e lança exceção")
    void shouldExceptionNotFoundBookForID() throws Exception {

        when(getBookService.find(anyLong())).thenThrow(new BookNotFoundException());

        mockMvc.perform(get(URI_BOOK + "/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getBookService).find(1L);
    }

    @Test
    @DisplayName("Lista os livros")
    void shouldListBooks() throws Exception {

        BookDTO book1 = createBookDTO().id(1L).title("Title List do Book 1").build();
        BookDTO book2 = createBookDTO().id(2L).resume("Resume List do Book2").build();
        BookDTO book3 = createBookDTO().id(3L).author("Author List do Book3").build();

        when(listBookService.listBooks()).thenReturn(Arrays.asList(book1, book2, book3));

        MvcResult mvcResult = mockMvc.perform(get(URI_BOOK)
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(3)))
                .andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(Arrays.asList(book1, book2, book3)))
                .isEqualToIgnoringWhitespace(resultResponseBody);

        verify(listBookService).listBooks();
    }

    @Test
    @DisplayName("Salva um livro")
    void shouldSaveBook() throws Exception {

        BookDTO bookDTO = createBookDTO().id(1L).build();

        mockMvc.perform(post(URI_BOOK)
                .contentType(CONT_TYPE)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andDo(print())
                .andExpect(status().isCreated());

        ArgumentCaptor<BookDTO> captorBook = ArgumentCaptor.forClass(BookDTO.class);
        verify(saveBookService).save(captorBook.capture());
        BookDTO result = captorBook.getValue();

        assertThat(result.getId()).isEqualTo(bookDTO.getId());
        assertThat(result.getTitle()).isEqualTo(bookDTO.getTitle());
        assertThat(result.getResume()).isEqualTo(bookDTO.getResume());
        assertThat(result.getAuthor()).isEqualTo(bookDTO.getAuthor());
        assertThat(result.getIsbn()).isEqualTo(bookDTO.getIsbn());
        assertThat(result.getYear()).isEqualTo(bookDTO.getYear());
    }

    @Test
    @DisplayName("Edita um livro")
    void shouldEditBook() throws Exception {

        Long id = 1L;
        BookDTO bookDTO = createBookDTO().id(id).build();
        bookDTO.setResume("Resumo alterado");

        mockMvc.perform(put(URI_BOOK + "/{id}", id)
                .contentType(CONT_TYPE)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());

        ArgumentCaptor<BookDTO> captorBook = ArgumentCaptor.forClass(BookDTO.class);
        ArgumentCaptor<Long> captorLong = ArgumentCaptor.forClass(Long.class);
        verify(updateBookService).edit(captorLong.capture(), captorBook.capture());
        BookDTO result = captorBook.getValue();

        assertThat(captorLong.getValue()).isEqualTo(id);
        assertThat(result.getId()).isEqualTo(bookDTO.getId());
        assertThat(result.getTitle()).isEqualTo(bookDTO.getTitle());
        assertThat(result.getResume()).isEqualTo(bookDTO.getResume());
        assertThat(result.getAuthor()).isEqualTo(bookDTO.getAuthor());
        assertThat(result.getIsbn()).isEqualTo(bookDTO.getIsbn());
        assertThat(result.getYear()).isEqualTo(bookDTO.getYear());
    }

    @Test
    @DisplayName("Deleta um livro")
    void shouldDeleteBook() throws Exception {

        mockMvc.perform(delete(URI_BOOK + "/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteBookService).delete(1L);
    }

    /*@Test
    @DisplayName("Lança uma exceção de Book Not Found ao deletar")
    void shouldBookNotFoundException() throws Exception {

        //when(deleteBookService).thenThrow().thenThrow(new BookNotFoundException()); //como vou mockar para lançar uma exceção se o delete não usa uma chamada de metodo?

        mockMvc.perform(delete(URI_BOOK+"/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(deleteBookService).delete(1L);
    }*/
}