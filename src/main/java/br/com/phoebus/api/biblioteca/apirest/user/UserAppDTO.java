package br.com.phoebus.api.biblioteca.apirest.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderClassName = "Builder")
public class UserAppDTO {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private int age;

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
