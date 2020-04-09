package br.com.phoebus.api.biblioteca.apirest.loan.v1;


import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.service.DeleteLoan;
import br.com.phoebus.api.biblioteca.apirest.loan.service.EditLoan;
import br.com.phoebus.api.biblioteca.apirest.loan.service.GetLoan;
import br.com.phoebus.api.biblioteca.apirest.loan.service.ListLend;
import br.com.phoebus.api.biblioteca.apirest.loan.service.SaveLoan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/loan")
public class LoanController {

    private final SaveLoan saveLoan;
    private final DeleteLoan deleteLoan;
    private final ListLend listLend;
    private final GetLoan getLoan;
    private final EditLoan editLoan;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postLoan(@Valid @RequestBody LoanDTO newLoanDTO) {
        saveLoan.save(newLoanDTO);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLoanById(@PathVariable(value = "id") long id) {
        deleteLoan.delete(id);
    }

    @GetMapping
    List<LoanDTO> findAll() {
        return listLend.listLend();
    }

    @GetMapping(value = "/{id}")
    LoanDTO getLoan(@PathVariable(value = "id") long id) {
        return getLoan.getLoan(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putLoan(@Valid @PathVariable(value = "id") long id, @RequestBody LoanDTO attLoanDTO) {
        editLoan.edit(id, attLoanDTO);
    }
}
