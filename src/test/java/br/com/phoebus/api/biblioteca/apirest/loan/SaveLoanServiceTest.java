package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.loan.service.SaveLoanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.phoebus.api.biblioteca.apirest.loan.builders.LoanDTOBuilder.createLoanDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do serviço em salvar um emprestimo")
public class SaveLoanServiceTest {

    @Mock
    private LoanRepository repository;

    private SaveLoanServiceImpl saveLoan;

    @BeforeEach
    void setUp() {
        this.saveLoan = new SaveLoanServiceImpl(repository);
    }

    @Test
    @DisplayName("deve salvar um emprestimo")
    void shouldSaveLoan() {

        LoanDTO loanDTO = createLoanDTO().build();

        saveLoan.save(loanDTO);

        ArgumentCaptor<Loan> captor = ArgumentCaptor.forClass(Loan.class);
        verify(repository).save(captor.capture());

        Loan result = captor.getValue();

        Assertions.assertAll("Loan",
                () -> assertThat(result.getLoanTime(), is(loanDTO.getLoanTime()))
        );
    }
}
