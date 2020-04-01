package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteLoanImpl implements DeleteLoan{

    private final LoanRepository repository;

    @Override
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch (LoanNotFoundException e){
            throw new LoanNotFoundException();
        }
    }
}
