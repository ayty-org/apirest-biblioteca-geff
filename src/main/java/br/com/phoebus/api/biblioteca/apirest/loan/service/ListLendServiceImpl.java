package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListLendServiceImpl implements ListLendService {

    private final LoanRepository repository;

    @Override
    public List<LoanDTO> listLend() {
        List<Loan> lend = repository.findAll();
        return LoanDTO.from(lend);
    }
}
