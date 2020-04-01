package br.com.phoebus.api.biblioteca.apirest.user;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserAppDTO {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotEmpty(message = "Name may not be empty")
    @Size(min = 2)
    private String name;

    @Min(3)
    private int age;

    @NotEmpty(message = "Telephone may not be empty")
    @Size(min = 8)
    private String telephone;

    /*public UserAppDTO(UserApp userApp){
        this.id = userApp.getId();
        this.age = userApp.getAge();
        this.name = userApp.getName();
        this.telephone = userApp.getTelephone();
    }*/

    public static UserAppDTO from(UserApp userApp){
        return UserAppDTO.builder()
                .id(userApp.getId())
                .age(userApp.getAge())
                .name(userApp.getName())
                .telephone(userApp.getTelephone())
                .build();
    }

    public static UserApp to(UserAppDTO userAppDTO){
        return UserApp.builder()
                .id(userAppDTO.getId())
                .age(userAppDTO.getAge())
                .name(userAppDTO.getName())
                .telephone(userAppDTO.getTelephone())
                .build();
    }

}
