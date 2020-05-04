package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanInconsistencyInDataException;
import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.service.EditLoanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
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

        Long id = 1L;
        LoanDTO loanDTO = createLoanDTO()
                .loanTime("Data Edit").build();

        Loan loan = createLoan().build();
        loan.setId(id);
        Optional<Loan> loanOptional = Optional.of(loan);
        when(repository.findById(anyLong())).thenReturn(loanOptional);

        editLoan.edit(id,loanDTO);

        ArgumentCaptor<Loan> captor = ArgumentCaptor.forClass(Loan.class);
        verify(repository).save(captor.capture());

        Loan result = captor.getValue();

        assertAll("Loan",
                () -> assertThat(result.getLoanTime(), is(loanDTO.getLoanTime())),
                () -> assertThat(result.getBooksLends().size(), is(2)),
                () -> assertThat(result.getBooksLends().get(0).getAuthor(), is(loanDTO.getBooks().get(0).getAuthor()))
        );
    }

    @Test
    @DisplayName("Deve lançar uma exceção")
    void shouldLoanIncosistencyInDataException() {
        LoanDTO loanDTO = createLoanDTO()
                .loanTime("Data Edit").build();

        Loan loan = createLoan().build();
        //Optional<Loan> loanOptional = Optional.of(loan);
        when(repository.findById(anyLong())).thenThrow(new LoanInconsistencyInDataException());

        assertThrows(LoanInconsistencyInDataException.class, () -> editLoan.edit(anyLong(),loanDTO));

        verify(repository, times(0)).save(loan);
    }

    @Test
    @DisplayName("Deve lançar uma exceção Not Found")
    void shouldNotFoundException() {
        LoanDTO loanDTO = createLoanDTO()
                .loanTime("Data Edit").build();

        Loan loan = createLoan().build();
        Optional<Loan> loanOptional = Optional.of(loan);

        when(repository.findById(anyLong())).thenThrow(new LoanNotFoundException());

        Assertions.assertThrows(LoanNotFoundException.class, () -> editLoan.edit(3L,loanDTO));

        verify(repository, times(0)).save(loan);

    }
}
