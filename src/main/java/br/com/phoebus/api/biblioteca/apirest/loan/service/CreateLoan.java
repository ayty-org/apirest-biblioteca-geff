package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;

@FunctionalInterface
public interface CreateLoan {

    void create(Loan newLoan);
}
