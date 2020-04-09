package br.com.phoebus.api.biblioteca.apirest.user.builders;

import br.com.phoebus.api.biblioteca.apirest.user.UserAppDTO;

public class UserAppDTOBuilder {

    public static UserAppDTO.Builder createUserAppDTO() {
        return UserAppDTO.builder()
                .age(22)
                .name("Name Test")
                .telephone("99 9 999999999");
    }
}
