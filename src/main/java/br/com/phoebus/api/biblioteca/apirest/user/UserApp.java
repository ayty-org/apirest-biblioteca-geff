package br.com.phoebus.api.biblioteca.apirest.user;

import br.com.phoebus.api.biblioteca.apirest.loan.Loan;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userapp")
@Builder(builderClassName = "Builder")
public class UserApp implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private int age;

    private String telephone;

    @OneToMany(mappedBy = "userApp")
    private List<Loan> lend = new ArrayList<>();
    //private Set<Loan> lend = new HashSet<>();

    public static UserApp to(UserAppDTO userAppDTO) {
        return UserApp.builder()
                .id(userAppDTO.getId())
                .telephone(userAppDTO.getTelephone())
                .name(userAppDTO.getName())
                .age(userAppDTO.getAge())
                .build();
    }

    public static List<UserApp> to(List<UserAppDTO> userAppDTOS) {
        List<UserApp> userApps = new ArrayList<>();
        for (UserAppDTO userAppDTO : userAppDTOS) {
            userApps.add(UserApp.to(userAppDTO));
        }
        return userApps;
    }

    public static Page<UserApp> to(Page<UserAppDTO> pages) {
        return pages.map(UserApp::to);
    }
}
