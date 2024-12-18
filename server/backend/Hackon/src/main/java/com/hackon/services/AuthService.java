package com.hackon.services;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private Keycloak keycloak;

    public AuthService(
            @Value("${keycloak.server-url}") String serverUrl,
            @Value("${keycloak.username}") String username,
            @Value("${keycloak.password}") String password
    ) {

        this.keycloak = KeycloakBuilder
                .builder()
                .serverUrl(serverUrl)
                .realm("master")
                .clientId("admin-cli")
                .username(username)
                .password(password)
                .build();
    }

    public Keycloak getKeycloak() {
        return keycloak;
    }
}
