package com.dtour.emailservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class EmailVerificationToken extends BaseModel {
    private UUID receiverUserId;
    private String token;
}
