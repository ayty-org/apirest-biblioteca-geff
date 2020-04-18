package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface ListPageLendService {
    Page<LoanDTO> ListLoanOnPage(Integer page, Integer size);
}
