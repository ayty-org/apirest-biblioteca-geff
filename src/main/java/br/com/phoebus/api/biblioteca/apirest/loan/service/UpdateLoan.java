package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;

@FunctionalInterface
public interface UpdateLoan {

    void update(Long id, LoanDTO loanDTO);
}
