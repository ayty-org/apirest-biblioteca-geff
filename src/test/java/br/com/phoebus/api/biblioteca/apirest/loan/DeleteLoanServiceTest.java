package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.service.DeleteLoanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade do servoço referente a deletar um emprestimo")
public class DeleteLoanServiceTest {

    @Mock
    private LoanRepository repository;

    private DeleteLoanServiceImpl deleteLoan;

    @BeforeEach
    void setUp() {
        this.deleteLoan = new DeleteLoanServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve deletar um emprestimo")
    void shouldDeleteLoan() {
        when(repository.existsById(anyLong())).thenReturn(true);

        deleteLoan.delete(anyLong());

        verify(repository).deleteById(anyLong());

    }

    @Test
    @DisplayName("Deve lançar uma exceção loan not found exception")
    void shouldNotFoundException() {

        when(repository.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(LoanNotFoundException.class, () -> deleteLoan.delete(anyLong()));

        verify(repository, times(0)).deleteById(anyLong());

    }
}
