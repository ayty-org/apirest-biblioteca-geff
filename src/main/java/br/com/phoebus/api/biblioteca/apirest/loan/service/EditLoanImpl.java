package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditLoanImpl implements EditLoan {

    private final LoanRepository repository;

    @Override
    public void edit(Long id, LoanDTO loanDTO) {
        Loan loan = repository.findById(id).orElseThrow(LoanNotFoundException::new);

        loan.setLoanTime(loanDTO.getLoanTime());

        repository.save(loan);

    }
}
