package com.dtour.userservice.dto;

import com.dtour.userservice.model.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UesrDto {
    private String name;
    private String mobileNumber;
    private String telephoneNumber;
    private String emailAddress;
    private String password;
    private List<UserRole> roles;
    @Setter(AccessLevel.NONE)
    private boolean emailVerified;
}
