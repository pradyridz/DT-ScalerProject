package com.dtour.emailservice.service;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class TokenService implements ITokenService {
    @Value("${user-service.email.client.id}")
    private String clientId;
    @Value("${user-service.email.client.secret}")
    private String clientSecret;
    @Value("${user-service.email.client.token.uri}")
    private String authServerUrl;
    @Override
    public AccessToken getAccessToken() throws IOException, ParseException, URISyntaxException {
        AuthorizationGrant clientGrant = new ClientCredentialsGrant();
        ClientID clientID = new ClientID(clientId);
        Secret secret = new Secret(clientSecret);
        ClientAuthentication clientAuth = new ClientSecretBasic(clientID, secret);

        Scope scope = new Scope("API");
        URI tokenEndpoint = new URI(authServerUrl);
        TokenRequest request = new TokenRequest(tokenEndpoint, clientAuth, clientGrant, scope);

        TokenResponse response = TokenResponse.parse(request.toHTTPRequest().send());

        if (! response.indicatesSuccess()) {
            TokenErrorResponse errorResponse = response.toErrorResponse();
        }
        AccessTokenResponse successResponse = response.toSuccessResponse();
        return successResponse.getTokens().getAccessToken();
    }
}
