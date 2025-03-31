package com.dtour.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseModel {

    private String name;
    @NotNull
    @Column(unique = true)
    @NotEmpty
    private String mobileNumber;
    private String telephoneNumber;
    @NotNull
    @Column(unique = true)
    @NotEmpty
    private String emailAddress;
    private String encryptedPassword;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private UserStatus status;
    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    @NotEmpty
    private List<UserRole> roles;
    private boolean emailVerified;
}
