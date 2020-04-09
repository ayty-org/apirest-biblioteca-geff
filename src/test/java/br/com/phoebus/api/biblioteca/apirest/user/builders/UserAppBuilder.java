package br.com.phoebus.api.biblioteca.apirest.user.builders;

import br.com.phoebus.api.biblioteca.apirest.user.UserApp;

public class UserAppBuilder {

    public static UserApp.Builder createUserApp() {
        return UserApp.builder()
                .age(22)
                .name("Name Test")
                .telephone("99 9 999999999");
    }
}
