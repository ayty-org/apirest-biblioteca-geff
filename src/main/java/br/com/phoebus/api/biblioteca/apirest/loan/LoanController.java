package br.com.phoebus.api.biblioteca.apirest.loan;


import br.com.phoebus.api.biblioteca.apirest.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping
    List<Loan> findAll(){
        return service.findLend();
    }

    @GetMapping(value = "/{id}")
    Loan getLoan(@PathVariable(value = "id") long id){
        return service.findLoanById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postLoan(@Validated @RequestBody Loan newLoan){
        service.createLoan(newLoan);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLoanById(@PathVariable(value = "id")long id){
        service.deleteLoanById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putLoan(@Validated @PathVariable(value = "id")long id, @RequestBody Loan attLoan){
        service.updateLoan(attLoan);
    }
}
