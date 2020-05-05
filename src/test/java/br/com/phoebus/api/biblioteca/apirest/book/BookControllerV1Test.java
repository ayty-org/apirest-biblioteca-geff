package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.book.services.*;
import br.com.phoebus.api.biblioteca.apirest.book.v1.BookControllerV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        MvcResult mvcResult = mockMvc.perform(get(URI_BOOK+"/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isOk())
                /*.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author", is(bookDTO.getAuthor())))
                .andExpect(jsonPath("$.resume", is(bookDTO.getResume())))
                .andExpect(jsonPath("$.isbn", is(bookDTO.getIsbn())))
                .andExpect(jsonPath("$.title", is(bookDTO.getTitle())))*/.andReturn();

        String resultResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(bookDTO))
                .isEqualToIgnoringWhitespace(resultResponseBody);
    }

    @Test
    @DisplayName("Lista os livros")
    void shouldListBooks() throws Exception {

        BookDTO book1 = createBookDTO().id(1L).title("Title List do Book 1").build();
        BookDTO book2 = createBookDTO().id(2L).resume("Resume List do Book2").build();
        BookDTO book3 = createBookDTO().id(3L).author("Author List do Book3").build();

        when(listBookService.listBooks()).thenReturn(Arrays.asList(book1, book2, book3));

        mockMvc.perform(get(URI_BOOK)
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(3)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].year", is(book1.getYear())))
                .andExpect(jsonPath("$.[0].author", is(book1.getAuthor())))
                .andExpect(jsonPath("$.[0].isbn", is(book1.getIsbn())))
                .andExpect(jsonPath("$.[0].resume", is(book1.getResume())))
                .andExpect(jsonPath("$.[0].title", is(book1.getTitle())))
                .andExpect(jsonPath("$.[1].id", is(2)))
                .andExpect(jsonPath("$.[1].resume", is(book2.getResume())))
                .andExpect(jsonPath("$.[2].id", is(3)))
                .andExpect(jsonPath("$.[2].author", is(book3.getAuthor())));
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
        
    }

    @Test
    @DisplayName("Edita um livro")
    void shouldEditBook() throws Exception {

        BookDTO bookDTO = createBookDTO().id(1L).build();
        bookDTO.setResume("Resumo alterado");

        mockMvc.perform(put(URI_BOOK+"/{id}", 1L)
                .contentType(CONT_TYPE)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("Deleta um livro")
    void shouldDeleteBook() throws Exception {

        mockMvc.perform(delete(URI_BOOK+"/{id}", 1L)
                .contentType(CONT_TYPE))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteBookService).delete(1L);
    }
}