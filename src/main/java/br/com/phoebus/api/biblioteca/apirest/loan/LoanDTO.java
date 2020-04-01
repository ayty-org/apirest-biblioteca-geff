package br.com.phoebus.api.biblioteca.apirest.loan;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class LoanDTO {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotEmpty(message = "Loan time may not be empty")
    private String loanTime;

    /*public LoanDTO(Loan loan){
        this.id = loan.getId();
        this.loanTime = loan.getLoanTime();
    }*/

    public static LoanDTO from(Loan loan){
        return LoanDTO.builder()
                .id(loan.getId())
                .loanTime(loan.getLoanTime())
                .build();
    }

    public static Loan to(LoanDTO loanDTO){
        return Loan.builder()
                .id(loanDTO.getId())
                .loanTime(loanDTO.getLoanTime())
                .build();
    }
}
