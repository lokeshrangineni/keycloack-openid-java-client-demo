package com.redhat.openid.client.keycloak.config;

import lombok.val;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Configuration
public class keycloakClientConfig {

    @Value("${keycloak.credentials.secret}")
    private String secretKey;
    @Value("${keycloak.clientId}")
    private String clientId;
    @Value("${keycloak.auth-server-url}")
    private String authUrl;
    @Value("${keycloak.realm}")
    private String realm;

        @Bean(name = "keycloak")
        protected Keycloak keycloak() {
            return KeycloakBuilder.builder()
                    .grantType(CLIENT_CREDENTIALS)
                    .serverUrl(authUrl)
                    .realm(realm)
                    .clientId(clientId)
                    .clientSecret(secretKey)
                    .build();
        }

}
