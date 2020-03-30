package br.com.phoebus.api.biblioteca.apirest.loan;


import br.com.phoebus.api.biblioteca.apirest.loan.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/loan")
public class LoanController {

    private final CreateLoan createLoan;
    private final DeleteLoan deleteLoan;
    private final GetAllLend getAllLend;
    private final GetLoan getLoan;
    private final UpdateLoan updateLoan;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postLoan(@Validated @RequestBody Loan newLoan){
        createLoan.create(newLoan);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLoanById(@PathVariable(value = "id")long id){
        deleteLoan.delete(id);
    }

    @GetMapping
    List<Loan> findAll(){
        return getAllLend.getAllLend();
    }

    @GetMapping(value = "/{id}")
    Loan getLoan(@PathVariable(value = "id") long id){
        return getLoan.getLoan(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putLoan(@Validated @PathVariable(value = "id")long id, @RequestBody LoanDTO attLoanDTO){
        updateLoan.update(id, attLoanDTO);
    }
}
