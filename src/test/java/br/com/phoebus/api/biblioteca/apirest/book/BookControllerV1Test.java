package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.book.services.DeleteBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.EditBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.GetBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.ListBooksService;
import br.com.phoebus.api.biblioteca.apirest.book.services.ListPageBookService;
import br.com.phoebus.api.biblioteca.apirest.book.services.SaveBookService;
import br.com.phoebus.api.biblioteca.apirest.book.v1.BookControllerV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookDTOBuilder.createBookDTO;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookControllerV1.class)
@DisplayName("Valida funcionalidade do Controller Book")
public class BookControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBookService getBookService;
    @MockBean
    private ListBooksService listBookService;
    @MockBean
    private ListPageBookService listPageBookService;
    @MockBean
    private SaveBookService saveBookService;
    @MockBean
    private EditBookService updateBookService;
    @MockBean
    private DeleteBookService deleteBookService;

    @Test
    @DisplayName("Pesquisa livro por id")
    void whenValidGetIdBook_thenReturnsBook() throws Exception { //pesquisa por livro

        BookDTO bookDTO = createBookDTO().id(1L).build();

        when(getBookService.find(1L)).thenReturn(bookDTO);

        mockMvc.perform(get("/v1/api/book/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author", is("teste author")))
                .andExpect(jsonPath("$.resume", is("teste resume")))
                .andExpect(jsonPath("$.isbn", is("teste isbn")))
                .andExpect(jsonPath("$.title", is("teste title")));
    }
}