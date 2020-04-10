package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.loan.service.ListLendImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static br.com.phoebus.api.biblioteca.apirest.loan.builders.LoanBuilder.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço em listar todos os emprestimos")
public class LendListTest {

    @Mock
    private LoanRepository repository;

    private ListLendImpl listLend;

    @BeforeEach
    void setUp(){
        this.listLend = new ListLendImpl(repository);
    }

    @Test
    void shouldListLend() {

        Loan loan1 = createLoan().build();
        Loan loan2 = createLoan().build();
        Loan loan3 = createLoan().build();
        Loan loan4 = createLoan().build();
        when(repository.findAll()).thenReturn(Arrays.asList(loan1,loan2,loan3,loan4));

        List<LoanDTO> result = this.listLend.listLend();

        assertAll("Lend",
                () -> assertThat(result.size(), is(4)),
                () -> assertThat(result.get(0).getLoanTime(), is(loan1.getLoanTime())),

                () -> assertThat(result.get(1).getLoanTime(), is(loan2.getLoanTime())),

                () -> assertThat(result.get(2).getLoanTime(), is(loan3.getLoanTime()))
        );
    }
}
