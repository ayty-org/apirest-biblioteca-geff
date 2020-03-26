package br.com.phoebus.api.biblioteca.apirest.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {

    LoanModel findById(long id);
}
