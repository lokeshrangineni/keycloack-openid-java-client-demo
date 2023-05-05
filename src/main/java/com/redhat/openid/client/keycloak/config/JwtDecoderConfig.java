
package com.redhat.openid.client.keycloak.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.util.*;

@Configuration
public class JwtDecoderConfig {
    @Value("${spring.security.oauth2.client.provider.keycloak.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        jwtDecoder.setClaimSetConverter(new OrganizationSubClaimAdapter());
        return jwtDecoder;
    }
}

@Configuration
class OrganizationSubClaimAdapter implements
        Converter<Map<String, Object>, Map<String, Object>> {

    private final MappedJwtClaimSetConverter delegate =
            MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

    public Map<String, Object> convert(Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.delegate.convert(claims);

        String organization = convertedClaims.get("organization") != null ?
                (String) convertedClaims.get("organization") : "unknown";
        convertedClaims.put("organization", organization.toUpperCase());

        Map<String,Object> realmAccessMap = convertedClaims.get("realm_access") != null ?
                (Map<String, Object>) convertedClaims.get("realm_access") : new HashMap<>();
        List<String> realmAccessRoles = realmAccessMap.get("roles") != null ?
                (List<String>) realmAccessMap.get("roles") : new ArrayList<String>();
        convertedClaims.put("roles",realmAccessRoles);

        return convertedClaims;
    }
}

