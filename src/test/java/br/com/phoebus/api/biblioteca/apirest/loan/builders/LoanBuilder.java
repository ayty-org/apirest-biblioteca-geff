package br.com.phoebus.api.biblioteca.apirest.loan.builders;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;

public class LoanBuilder {

    public static Loan.Builder createLoan() {
        return Loan.builder().loanTime("10/04/2020");
    }
}
