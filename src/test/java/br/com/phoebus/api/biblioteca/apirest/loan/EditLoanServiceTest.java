package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanInconsistencyInDataException;
import br.com.phoebus.api.biblioteca.apirest.loan.service.EditLoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.phoebus.api.biblioteca.apirest.loan.builders.LoanBuilder.createLoan;
import static br.com.phoebus.api.biblioteca.apirest.loan.builders.LoanDTOBuilder.createLoanDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade doo serviço em editar um emprestimo")
public class EditLoanServiceTest {

    @Mock
    private LoanRepository repository;

    private EditLoanServiceImpl editLoan;

    @BeforeEach
    void setUp() {
        this.editLoan = new EditLoanServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve editar um emprestimo")
    void shouldEditLoan() {

        LoanDTO loanDTO = createLoanDTO()
                .loanTime("Data Edit").build();

        Loan loan = createLoan().build();
        Optional<Loan> loanOptional = Optional.of(loan);
        when(repository.findById(anyLong())).thenReturn(loanOptional);

        editLoan.edit(anyLong(),loanDTO);

        ArgumentCaptor<Loan> captor = ArgumentCaptor.forClass(Loan.class);
        verify(repository).save(captor.capture());

        Loan result = captor.getValue();

        assertAll("Loan",
                () -> assertThat(result.getLoanTime(), is(loanDTO.getLoanTime()))
        );
    }

    @Test
    @DisplayName("Deve lançar uma exceção")
    void shouldLoanIncosistencyInDataException() {
        LoanDTO loanDTO = createLoanDTO()
                .loanTime("Data Edit").build();

        Loan loan = createLoan().build();
        Optional<Loan> loanOptional = Optional.of(loan);
        when(repository.findById(anyLong())).thenThrow(new LoanInconsistencyInDataException());

        assertThrows(LoanInconsistencyInDataException.class, () -> editLoan.edit(anyLong(),loanDTO));

        verify(repository, times(0)).save(any());
    }
}
