package com.dtour.userservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name="userId")
public class Admin extends User {

    private String firstName;
    @NotNull
    @NotEmpty
    private String LastName;
    @OneToOne(fetch = FetchType.LAZY)
    private Address permanentAddress;
    @OneToOne(fetch = FetchType.LAZY)
    private Address currentAddress;
}
