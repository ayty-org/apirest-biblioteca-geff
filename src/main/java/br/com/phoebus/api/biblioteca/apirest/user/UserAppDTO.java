package br.com.phoebus.api.biblioteca.apirest.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserAppDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Name may not be empty")
    @Size(min = 2)
    private String name;

    @Min(3)
    private int age;

    @NotEmpty(message = "Telephone may not be empty")
    @Size(min = 8)
    private String telephone;

    public static UserAppDTO from(UserApp userApp) {
        return UserAppDTO.builder()
                .id(userApp.getId())
                .age(userApp.getAge())
                .name(userApp.getName())
                .telephone(userApp.getTelephone())
                .build();
    }

    public static List<UserAppDTO> from(List<UserApp> users) {
        List<UserAppDTO> userAppDTOS = new ArrayList<>();
        for (UserApp userApp : users) {
            userAppDTOS.add(UserAppDTO.from(userApp));
        }
        return userAppDTOS;
    }

    public static Page<UserAppDTO> from(Page<UserApp> pages) {
        return pages.map(UserAppDTO::from);
    }
}
