package com.dtour.userservice.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "address")
public class Address extends BaseModel {
    @NotNull
    @NotEmpty
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    @NotNull
    @NotEmpty
    private String city;
    private String district;
    private String state;
    @NotNull
    @NotEmpty
    private String country;
    @NotNull
    @NotEmpty
    private String postalCode;
}
