package br.com.phoebus.api.biblioteca.apirest.loan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping
    public ResponseEntity<List<LoanModel>> findAll(){
        List<LoanModel> lend = service.findLend();
        return ResponseEntity.ok().body(lend);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<LoanModel> getLoan(@PathVariable(value = "id") long id){
        LoanModel loan = service.findLoanById(id);
        if (loan == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(loan);
    }

    @PostMapping
    ResponseEntity<LoanModel> postLoan(@RequestBody LoanModel newLoan){
        service.createLoan(newLoan);
        return ResponseEntity.ok().body(newLoan);
    }

    @DeleteMapping
    ResponseEntity deleteLoan(@RequestBody LoanModel deleteLoan){
        LoanModel delLoan = service.findLoanById(deleteLoan.getId());
        if (delLoan == deleteLoan){
            service.deleteLoan(deleteLoan);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/id")
    ResponseEntity deleteLoanById(@PathVariable(value = "id")long id){
        LoanModel delLoan = service.findLoanById(id);
        if (delLoan == null){
            return ResponseEntity.badRequest().build();
        }
        service.deleteLoanById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity putLoan(@RequestBody LoanModel attLoan){
        LoanModel loan = service.findLoanById(attLoan.getId());
        if (loan == null){
            return ResponseEntity.badRequest().build();
        }
        service.updateLoan(attLoan);
        return ResponseEntity.ok().body(attLoan);
    }
}
