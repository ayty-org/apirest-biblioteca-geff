package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.service.GetLoanImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.phoebus.api.biblioteca.apirest.loan.builders.LoanBuilder.createLoan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço em buscar um emprestimo")
public class LoanGetTest {

    @Mock
    private LoanRepository repository;

    private GetLoanImpl getLoan;

    @BeforeEach
    void setUp() {
        this.getLoan = new GetLoanImpl(repository);
    }

    @Test
    @DisplayName("Deve pegar um emprestimo")
    void shouldGetLoan() {

        Loan loan = createLoan().build();
        Optional<Loan> optionalLoan = Optional.of(loan);
        when(repository.findById(anyLong())).thenReturn(optionalLoan);

        LoanDTO result = this.getLoan.getLoan(anyLong());

        assertAll("Loan",
                () -> assertThat(result.getLoanTime(), is(loan.getLoanTime())));

    }

    @Test
    @DisplayName("Deve lançar uma exceção")
    void shouldNotFoundLoanException() {
        when(repository.findById(anyLong())).thenThrow(new LoanNotFoundException());

        assertThrows(LoanNotFoundException.class, () -> getLoan.getLoan(anyLong()));
        verify(repository, times(0)).findById(anyLong());
    }
}
