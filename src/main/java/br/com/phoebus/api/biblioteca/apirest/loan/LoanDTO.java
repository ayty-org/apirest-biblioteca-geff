package br.com.phoebus.api.biblioteca.apirest.loan;


import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class LoanDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Loan time may not be empty")
    private String loanTime;

    @NotNull
    private UserAppDTO userApp;

    @NotEmpty
    private List<BookDTO> books;

    public static LoanDTO from(Loan loan) {
        return LoanDTO.builder()
                .id(loan.getId())
                .loanTime(loan.getLoanTime())
                .books(BookDTO.from(loan.getBooksLends()))
                .userApp(UserAppDTO.from(loan.getUserApp()))
                .build();
    }

    public static List<LoanDTO> from(List<Loan> lend) {
        List<LoanDTO> lendDTO = new ArrayList<>();
        for (Loan loan : lend) {
            lendDTO.add(LoanDTO.from(loan));
        }
        return lendDTO;
    }

    public static Page<LoanDTO> from(Page<Loan> pages) {
        return pages.map(LoanDTO::from);
    }
}
