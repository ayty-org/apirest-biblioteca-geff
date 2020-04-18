package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;

import java.util.List;

@FunctionalInterface
public interface ListLendService {

    List<LoanDTO> listLend();
}
