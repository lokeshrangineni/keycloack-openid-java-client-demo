package com.redhat.openid.client.keycloack.openid.client.controller;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserRestService {

    @Autowired
    private Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    @GetMapping("/user-info")
    public Jwt getOidcUserPrincipal(
            @AuthenticationPrincipal Jwt principal) {
        return principal;
    }

    @GetMapping("/users")
    public List<UserRepresentation> findUsers(
            @AuthenticationPrincipal Jwt principal) {
        return keycloak.realm(realm).users().list();
    }
}
