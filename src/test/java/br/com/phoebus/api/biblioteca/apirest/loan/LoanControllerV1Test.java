package br.com.phoebus.api.biblioteca.apirest.loan;


import br.com.phoebus.api.biblioteca.apirest.loan.service.DeleteLoanService;
import br.com.phoebus.api.biblioteca.apirest.loan.v1.LoanController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(LoanController.class)
@DisplayName("Valida a funcionalidade do controller de Loan")
public class LoanControllerV1Test {

    private final String URI_LOAN = "v1/loan";
    private final String CONT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteLoanService deleteLoanService;
}
