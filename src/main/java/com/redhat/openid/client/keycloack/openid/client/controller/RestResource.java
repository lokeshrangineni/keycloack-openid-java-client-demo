package com.redhat.openid.client.keycloack.openid.client.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RestResource {

    @GetMapping("/user-info")
    public Jwt getOidcUserPrincipal(
            @AuthenticationPrincipal Jwt principal) {
        return principal;
    }

    @GetMapping("/users")
    public Jwt findUsers(
            @AuthenticationPrincipal Jwt principal) {
        return principal;
    }
}
