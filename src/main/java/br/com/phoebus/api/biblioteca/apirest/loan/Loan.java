package br.com.phoebus.api.biblioteca.apirest.loan;

import br.com.phoebus.api.biblioteca.apirest.book.Book;
import br.com.phoebus.api.biblioteca.apirest.user.UserApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan")
@Builder(builderClassName = "Builder")
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id")
    private Long id;

    private String loanTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp userApp;

    @ManyToMany
    @JoinTable(name = "lend_book",
            joinColumns = @JoinColumn(name = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> booksLends = new ArrayList<>();
    //private Set<Book> booksLends = new HashSet<>();

    public static Loan to(LoanDTO loanDTO) {
        return Loan.builder()
                .id(loanDTO.getId())
                .loanTime(loanDTO.getLoanTime())
                .userApp(UserApp.to(loanDTO.getUserApp()))
                .booksLends(Book.to(loanDTO.getBooks()))
                .build();
    }

    public static List<Loan> to(List<LoanDTO> lendDTO) {
        List<Loan> lend = new ArrayList<>();
        for (LoanDTO loanDTO : lendDTO) {
            lend.add(Loan.to(loanDTO));
        }
        return lend;
    }

    public static Page<Loan> to(Page<LoanDTO> pages) {
        return pages.map(Loan::to);
    }

}
