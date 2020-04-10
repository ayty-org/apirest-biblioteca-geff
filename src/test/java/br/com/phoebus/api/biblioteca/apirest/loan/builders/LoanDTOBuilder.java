package br.com.phoebus.api.biblioteca.apirest.loan.builders;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;

public class LoanDTOBuilder {
    public static LoanDTO.Builder createLoanDTO() {
        return LoanDTO.builder().loanTime("09/04/2020");
    }
}
