package br.com.phoebus.api.biblioteca.apirest.loan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repository;

    public LoanModel findLoanById(long id){
        return repository.findById(id);
    }

    public List<LoanModel> findLend(){
        return repository.findAll();
    }

    public LoanModel createLoan(LoanModel newLoan){
        repository.save(newLoan);
        return newLoan;
    }

    public LoanModel updateLoan(LoanModel attLoan){
        repository.save(attLoan);
        return attLoan;
    }

    public void deleteLoan(LoanModel delLoan){
        repository.delete(delLoan);
    }

    public void deleteLoanById(long id){
        repository.deleteById(id);
    }
}
