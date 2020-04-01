package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;

@FunctionalInterface
public interface EditLoan {

    void edit(Long id, LoanDTO loanDTO);
}
