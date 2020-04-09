package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.phoebus.api.biblioteca.apirest.loan.Loan.to;

@RequiredArgsConstructor
@Service
public class SaveLoanImpl implements SaveLoan {

    private final LoanRepository repository;

    @Override
    public void save(LoanDTO newLoanDTO) {
        repository.save(to(newLoanDTO));
    }
}
