package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanInconsistencyInDataException;
import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateLoanImpl implements UpdateLoan{

    private final LoanRepository repository;
    @Override
    public void update(Long id, LoanDTO loanDTO) {
        if (id == loanDTO.getId()){
            Loan attLoan = LoanDTO.to(loanDTO);
            repository.save(attLoan);
            return;
        }
        throw new LoanInconsistencyInDataException();
    }
}
