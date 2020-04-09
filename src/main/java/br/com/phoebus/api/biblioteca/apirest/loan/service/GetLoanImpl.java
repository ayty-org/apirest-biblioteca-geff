package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetLoanImpl implements GetLoan {

    private final LoanRepository repository;

    @Override
    public LoanDTO getLoan(Long id) {
        return LoanDTO.from(repository.findById(id).orElseThrow(LoanNotFoundException::new));
    }
}
