package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ListLendImpl implements ListLend {

    private final LoanRepository repository;

    @Override
    public List<LoanDTO> listLend() {
        List<Loan> lend = repository.findAll();
        List<LoanDTO> lendDTO = new ArrayList<LoanDTO>() {
        };
        for (Loan loan : lend) {
            lendDTO.add(LoanDTO.from(loan));
        }
        return lendDTO;
    }
}
