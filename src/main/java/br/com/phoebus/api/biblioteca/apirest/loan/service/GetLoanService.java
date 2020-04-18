package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;

@FunctionalInterface
public interface GetLoanService {

    LoanDTO getLoan(Long id);
}
