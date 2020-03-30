package br.com.phoebus.api.biblioteca.apirest.loan.service;

import br.com.phoebus.api.biblioteca.apirest.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteLoanImpl implements DeleteLoan{

    private final LoanRepository repository;

    @Override
    public void delete(Long id) {
        //Falar com kawe sobre lançar a exceção caso não encontre para deletar
        repository.deleteById(id);
    }
}
