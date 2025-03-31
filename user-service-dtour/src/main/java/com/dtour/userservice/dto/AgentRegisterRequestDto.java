package com.dtour.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentRegisterRequestDto {

    private String mobileNumber;
    private String telephoneNumber;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private AddressDto permanentAddress;
    private AddressDto currentAddress;
    private boolean currSameAsPermanentAddress;
    private CompanyDto company;
}
