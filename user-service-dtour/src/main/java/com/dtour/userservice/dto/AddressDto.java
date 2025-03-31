package com.dtour.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private String addresLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String district;
    private String state;
    private String country;
    private String postalCode;
}
