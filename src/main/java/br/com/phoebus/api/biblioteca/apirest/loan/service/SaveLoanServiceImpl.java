package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveLoanServiceImpl implements SaveLoanService {

    private final LoanRepository repository;

    @Override
    public void save(LoanDTO newLoanDTO) {
        repository.save(Loan.to(newLoanDTO));
    }
}
