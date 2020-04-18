package br.com.phoebus.api.biblioteca.apirest.book;

import br.com.phoebus.api.biblioteca.apirest.book.services.ListPageBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço em Listar os livros por paginas")
public class ListPageBookServiceTest {

    @Mock
    private BookRepository repository;

    private ListPageBookServiceImpl listPageBook;

    @BeforeEach
    void setUp() {
        this.listPageBook = new ListPageBookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve retornar os livros por paginação")
    void shouldFindBookPerPage() {

        Pageable pageRequest = PageRequest.of(0, 2,
                Sort.Direction.ASC, "id");
        Book book = createBook().build();
        when(repository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, book)));

        Page<BookDTO> result = listPageBook.ListBookOnPage(0, 2);
        assertAll("Books",
                () -> assertThat(result.getNumber(), is(0)),
                () -> assertThat(result.getSize(), is(2)),

                () -> assertThat(result.getContent().get(0).getAuthor(), is(book.getAuthor())),
                () -> assertThat(result.getContent().get(0).getIsbn(), is(book.getIsbn())),
                () -> assertThat(result.getContent().get(0).getResume(), is(book.getResume())),

                () -> assertThat(result.getContent().get(1).getAuthor(), is(book.getAuthor())),
                () -> assertThat(result.getContent().get(1).getResume(), is(book.getResume())),
                () -> assertThat(result.getContent().get(1).getTitle(), is(book.getTitle()))

        );
    }
}
