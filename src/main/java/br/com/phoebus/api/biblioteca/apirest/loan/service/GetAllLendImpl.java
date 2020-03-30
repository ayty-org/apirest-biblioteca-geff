package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllLendImpl implements GetAllLend {

    private LoanRepository repository;

    @Override
    public List<Loan> getAllLend() {
        return repository.findAll();
    }
}
