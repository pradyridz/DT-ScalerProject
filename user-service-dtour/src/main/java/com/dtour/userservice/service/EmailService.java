package com.dtour.userservice.service;

import com.dtour.userservice.exception.EmailNotificationException;
import com.nimbusds.oauth2.sdk.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class EmailService {

    private final RestTemplate restTemplate;
    private final ITokenService tokenService;
    @Value("${dtour.email.service.url}")
    private String emailServiceUrl;

    @Autowired
    public EmailService(RestTemplate restTemplate,
                        ITokenService tokenService) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    public ResponseEntity<String> sendRequestToEmailService(LinkedMultiValueMap<String, Object> formData) throws URISyntaxException, EmailNotificationException, IOException, ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(tokenService.getAccessToken().toString());
        try {
            RequestEntity<LinkedMultiValueMap<String, Object>> requestEntity = RequestEntity
                    .post(new URI(emailServiceUrl))
                    .headers(headers)
                    .body(formData);

            return  restTemplate.exchange(requestEntity, String.class);
        } catch (Exception e) {
            throw new EmailNotificationException(e.getCause());
        }
    }
}
