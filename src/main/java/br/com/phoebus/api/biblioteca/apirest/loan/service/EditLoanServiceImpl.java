package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.exceptions.LoanNotFoundException;
import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditLoanServiceImpl implements EditLoanService {

    private final LoanRepository repository;

    @Override
    public void edit(Long id, LoanDTO loanDTO) {
        Loan loan = repository.findById(id).orElseThrow(LoanNotFoundException::new);

        loan.setLoanTime(loanDTO.getLoanTime());
        loan.setBooksLends(Book.to(loanDTO.getBooks()));
        loan.setUserApp(UserApp.to(loanDTO.getUserApp()));

        repository.save(loan);

    }
}
