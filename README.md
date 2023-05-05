##keycloak-openid-oauth2-java-client-demo
As part of this application we are trying to secure the rest endpoint using openid - oauth2 specification.

#### Why this service required?
We are using keycloak as our openid server. The main purpose of this service is used as a proxy service to get data from the actual keycloak instance. The rest APIs of keycloak won't provide much granular control of permissions or roles of who can perform certain operations. This is the reason we are creating this proxy service so that we can have much more granular control.

#### Why we have two keycloak configurations?
Below configurations are used to retrieve the actual data from keycloak rest end points. This user is expected to have superior permissions to be able to execute all the keycloak rest end points serve on behalf of the actual logged in user to this service.

```properties
keycloak.realm=keycloack-role-rest-auth-setup
keycloak.clientId=loki-babak-test
keycloak.credentials.secret=Irx3b0SRG9zZ4oqr8Q1IzkfdZIi9vrOh
keycloak.auth-server-url=http://localhost:8080
```

Below configurations are used to take care of the authorization to the current service. Technically this can be pointed to different keycloak instance/realm. All the rest service exposed in this service can be controlled to have restricted permissions, and we can achieve more granular control.

```properties
spring.security.oauth2.client.registration.keycloack.client-id=loki-babak-test
spring.security.oauth2.client.registration.keycloack.client-secret=Irx3b0SRG9zZ4oqr8Q1IzkfdZIi9vrOh
spring.security.oauth2.client.registration.keycloack.scope=openid
spring.security.oauth2.client.registration.keycloack.redirect-uri=http://localhost:3000/home
```




