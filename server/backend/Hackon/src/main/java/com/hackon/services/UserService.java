package com.hackon.services;

import com.hackon.entities.User;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private AuthService authService;


    public User createUser(User user, String realm) {
        UsersResource userResource = authService.getKeycloak()
                .realm(realm)
                .users();

        List<RoleRepresentation> availableRoles = authService.getKeycloak()
                .realm(realm)
                .roles()
                .list();

        List<RoleRepresentation> rolesToAssign = availableRoles.stream()
                .filter(role -> role.getName().contains("USER"))
                .collect(Collectors.toList());

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        Response response = userResource.create(userRepresentation);

        if (response.getStatus() == 201) {
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            authService.getKeycloak()
                    .realm(realm)
                    .users()
                    .get(userId)
                    .resetPassword(createPasswordCredential(user.getPassword()));

            authService.getKeycloak()
                            .realm(realm)
                                    .users()
                                            .get(userId)
                                                    .roles().realmLevel().add(rolesToAssign);

            user.setId(userId);
            return user;
        } else {
            throw new RuntimeException("Erro ao criar usuário: " + response.getStatusInfo());
        }
    }

    private org.keycloak.representations.idm.CredentialRepresentation createPasswordCredential(String password) {
        org.keycloak.representations.idm.CredentialRepresentation credential = new org.keycloak.representations.idm.CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType("password");
        credential.setValue(password);
        return credential;
    }

}
