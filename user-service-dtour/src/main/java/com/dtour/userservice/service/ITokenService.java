package com.dtour.userservice.service;


import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.token.AccessToken;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ITokenService {
    public AccessToken getAccessToken() throws IOException, ParseException, URISyntaxException;

}
