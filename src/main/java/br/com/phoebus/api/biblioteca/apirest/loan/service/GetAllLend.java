package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;

import java.util.List;

@FunctionalInterface
public interface GetAllLend {

    List<Loan> getAllLend();
}
