package br.com.phoebus.api.biblioteca.apirest.loan.service;


import br.com.phoebus.api.biblioteca.apirest.exceptions.NotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanRepository repository;

    public Loan findLoanById(long id){
        verifyLoanExist(id);
        return repository.findById(id);
    }

    public List<Loan> findLend(){
        return repository.findAll();
    }

    public void createLoan(Loan newLoan){
        repository.save(newLoan);
    }

    public void updateLoan(Loan attLoan){
        verifyLoanExist(attLoan.getId());
        repository.save(attLoan);
    }

    public void deleteLoan(Loan delLoan){
        repository.delete(delLoan);
    }

    public void deleteLoanById(long id){
        verifyLoanExist(id);
        repository.deleteById(id);
    }

    private void verifyLoanExist(long id){
        if (repository.findById(id) == null){
            throw new NotFoundException("Loan not found for ID: "+id);
        }
    }
}
