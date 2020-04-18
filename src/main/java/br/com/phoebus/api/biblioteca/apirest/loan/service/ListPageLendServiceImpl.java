package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListPageLendServiceImpl implements ListPageLendService {
    private final LoanRepository repository;

    @Override
    public Page<LoanDTO> ListLoanOnPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return LoanDTO.from(repository.findAll(pageRequest));
    }
}
