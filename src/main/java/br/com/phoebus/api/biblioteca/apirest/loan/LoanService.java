package br.com.phoebus.api.biblioteca.apirest.loan;


import br.com.phoebus.api.biblioteca.apirest.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanRepository repository;

    public LoanModel findLoanById(long id){
        verifyLoanExist(id);
        return repository.findById(id);
    }

    public List<LoanModel> findLend(){
        return repository.findAll();
    }

    public void createLoan(LoanModel newLoan){
        repository.save(newLoan);
    }

    public void updateLoan(LoanModel attLoan){
        verifyLoanExist(attLoan.getId());
        repository.save(attLoan);
    }

    public void deleteLoan(LoanModel delLoan){
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
