package br.com.phoebus.api.biblioteca.apirest.loan.builders;

import br.com.phoebus.api.biblioteca.apirest.book.BookDTO;
import br.com.phoebus.api.biblioteca.apirest.loan.LoanDTO;

import java.util.ArrayList;
import java.util.List;

import static br.com.phoebus.api.biblioteca.apirest.book.builders.BookDTOBuilder.createBookDTO;
import static br.com.phoebus.api.biblioteca.apirest.user.builders.UserAppDTOBuilder.createUserAppDTO;

public class LoanDTOBuilder {
    public static LoanDTO.Builder createLoanDTO() {
        return LoanDTO.builder()
                .loanTime("09/04/2020")
                .userApp(createUserAppDTO()
                        .name("Name Loan DTO Test").build());
    }

    public static List<BookDTO> booksDTO() {
        ArrayList<BookDTO> booksDTO = new ArrayList<BookDTO>();
        booksDTO.add(createBookDTO().id(1L).author("Author Loan Test DTO").build());
        booksDTO.add(createBookDTO().id(2L).title("Title Loan Test DTO").build());
        return booksDTO;
    }
}
