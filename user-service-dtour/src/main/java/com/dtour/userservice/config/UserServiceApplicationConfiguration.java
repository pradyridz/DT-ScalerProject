package com.dtour.userservice.config;

import com.dtour.userservice.service.AddressComponent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserServiceApplicationConfiguration {

    @Bean
    public AddressComponent createAddressComponent() {
        return new AddressComponent();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(14);
    }

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplateBuilder().build();
    }

}
