package br.com.phoebus.api.biblioteca.apirest.loan.v1;


import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.service.DeleteLoanService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.EditLoanService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.GetLoanService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.ListLendService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.ListPageLendService;
import br.com.phoebus.api.biblioteca.apirest.loan.service.SaveLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/loan")
public class LoanControllerV1 {

    private final SaveLoanService saveLoanService;
    private final DeleteLoanService deleteLoanService;
    private final ListLendService listLendService;
    private final ListPageLendService listPageLendService;
    private final GetLoanService getLoanService;
    private final EditLoanService editLoanService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postLoan(@Valid @RequestBody LoanDTO newLoanDTO) {
        saveLoanService.save(newLoanDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLoanById(@PathVariable(value = "id") long id) {
        deleteLoanService.delete(id);
    }

    @GetMapping
    List<LoanDTO> findAll() {
        return listLendService.listLend();
    }

    @GetMapping(value = "/{id}")
    LoanDTO getLoan(@PathVariable(value = "id") long id) {
        return getLoanService.getLoan(id);
    }

    @GetMapping(params = {"page", "size"})
    Page<LoanDTO> fundPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return listPageLendService.ListLoanOnPage(page, size);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putLoan(@Valid @PathVariable(value = "id") long id, @RequestBody LoanDTO attLoanDTO) {
        editLoanService.edit(id, attLoanDTO);
    }
}
