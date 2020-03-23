package br.com.phoebus.api.biblioteca.apirest.loan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<LoanModel> findAll(){
        return service.findLend();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    LoanModel getLoan(@PathVariable(value = "id") long id){
        return service.findLoanById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postLoan(@Validated @RequestBody LoanModel newLoan){
        service.createLoan(newLoan);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLoanById(@PathVariable(value = "id")long id){
        service.deleteLoanById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putLoan(@Validated @RequestBody LoanModel attLoan){
        service.updateLoan(attLoan);
    }
}
