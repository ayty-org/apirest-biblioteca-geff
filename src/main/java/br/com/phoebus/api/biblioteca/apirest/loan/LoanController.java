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

    private final SaveLoan saveLoan;
    private final DeleteLoan deleteLoan;
    private final ListLend listLend;
    private final GetLoan getLoan;
    private final EditLoan editLoan;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postLoan(@Validated @RequestBody LoanDTO newLoanDTO){
        saveLoan.save(newLoanDTO);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLoanById(@PathVariable(value = "id")long id){
        deleteLoan.delete(id);
    }

    @GetMapping
    List<LoanDTO> findAll(){
        return listLend.listLend();
    }

    @GetMapping(value = "/{id}")
    LoanDTO getLoan(@PathVariable(value = "id") long id){
        return getLoan.getLoan(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putLoan(@Validated @PathVariable(value = "id")long id, @RequestBody LoanDTO attLoanDTO){
        editLoan.edit(id, attLoanDTO);
    }
}
