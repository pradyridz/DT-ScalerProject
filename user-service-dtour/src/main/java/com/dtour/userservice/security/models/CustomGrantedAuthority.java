package com.dtour.userservice.security.models;


import com.dtour.userservice.model.UserRole;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    //    private Role role;
    private String authority;

    public CustomGrantedAuthority() {}


    public CustomGrantedAuthority(UserRole role) {
        this.authority = role.getName();
    }

    @Override
    public String getAuthority() {
//        return role.getName();
        return authority;
    }
}