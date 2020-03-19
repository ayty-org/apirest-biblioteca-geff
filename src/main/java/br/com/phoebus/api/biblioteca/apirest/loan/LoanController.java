package br.com.phoebus.api.biblioteca.apirest.loan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/loan")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping
    public ResponseEntity<List<LoanModel>> findAll(){
        List<LoanModel> lend = loanRepository.findAll();
        return ResponseEntity.ok().body(lend);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<LoanModel> getLoan(@PathVariable(value = "id") long id){
        LoanModel loan = loanRepository.findById(id);
        if (loan == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(loan);
    }

    @PostMapping
    ResponseEntity<LoanModel> postLoan(@RequestBody LoanModel newLoan){
        loanRepository.save(newLoan);
        return ResponseEntity.ok().body(newLoan);
    }

    @DeleteMapping
    ResponseEntity deleteLoan(@RequestBody LoanModel deleteLoan){
        LoanModel delLoan = loanRepository.findById(deleteLoan.getId());
        if (delLoan == deleteLoan){
            loanRepository.delete(deleteLoan);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/id")
    ResponseEntity deleteLoanById(@PathVariable(value = "id")long id){
        LoanModel delLoan = loanRepository.findById(id);
        if (delLoan == null){
            return ResponseEntity.badRequest().build();
        }
        loanRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity putLoan(@RequestBody LoanModel attLoan){
        LoanModel loan = loanRepository.findById(attLoan.getId());
        if (loan == null){
            return ResponseEntity.badRequest().build();
        }
        loanRepository.save(attLoan);
        return ResponseEntity.ok().body(attLoan);
    }
}
