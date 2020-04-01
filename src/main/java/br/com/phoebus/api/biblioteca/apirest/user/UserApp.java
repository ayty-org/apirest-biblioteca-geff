package br.com.phoebus.api.biblioteca.apirest.user;


import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="userapp")
@Builder(builderClassName = "Builder")
public class UserApp implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    private String name;

    private int age;

    private String telephone;

    @OneToMany(mappedBy = "user")
    private Set<Loan> lend;

}
