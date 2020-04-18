package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteLoanServiceImpl implements DeleteLoanService {

    private final LoanRepository repository;

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new LoanNotFoundException();
        }
        repository.deleteById(id);
    }
}
