package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.loan.service.ListPageLendServiceImpl;
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

import static br.com.phoebus.api.biblioteca.apirest.loan.builders.LoanBuilder.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do sereviço em listar os emprestimos por paginação")
public class ListPageLendServiceTest {

    @Mock
    private LoanRepository repository;

    private ListPageLendServiceImpl listPageLend;

    @BeforeEach
    void setUp() {
        this.listPageLend = new ListPageLendServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve retornar os emprestimos por paginação")
    void shouldFindLoanPerPage() {

        Pageable pageRequest = PageRequest.of(0,2,
                Sort.Direction.ASC, "id");
        Loan loan = createLoan().build();
        when(repository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, loan)));
        Page<LoanDTO> result = listPageLend.ListLoanOnPage(0,2);
        assertAll("Loan",
                () -> assertThat(result.getNumber(), is(0)),
                () -> assertThat(result.getSize(), is(2)),

                () -> assertThat(result.getContent().get(0).getLoanTime(), is(loan.getLoanTime())),
                () -> assertThat(result.getContent().get(0).getBooks().size(), is(loan.getBooksLends().size())),
                () -> assertThat(result.getContent().get(0).getUserApp().getName(), is(loan.getUserApp().getName())),

                () -> assertThat(result.getContent().get(1).getLoanTime(), is(loan.getLoanTime())),
                () -> assertThat(result.getContent().get(1).getBooks().size(), is(loan.getBooksLends().size())),
                () -> assertThat(result.getContent().get(1).getUserApp().getTelephone(), is(loan.getUserApp().getTelephone()))
        );
    }
}
