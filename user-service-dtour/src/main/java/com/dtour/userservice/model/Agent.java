package com.dtour.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "userId")
public class Agent extends User {

    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Address permanentAddress;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Address currentAddress;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Company company;
    @NotNull
    private boolean currSameAsPermanentAddress;
}
