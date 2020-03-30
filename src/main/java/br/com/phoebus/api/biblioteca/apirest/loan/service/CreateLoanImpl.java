package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateLoanImpl implements CreateLoan{

    private final LoanRepository repository;

    @Override
    public void create(Loan newLoan) {
        repository.save(newLoan);
    }
}
