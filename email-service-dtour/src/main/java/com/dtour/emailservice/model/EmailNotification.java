package com.dtour.emailservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class EmailNotification extends BaseModel{
    @NotNull
    private String mailTo;
    private UUID receiverUserId;
    @NotNull
    private String mailFrom;
    private String mailSubject;
    private String mailBody;
    private UUID senderUserId;
    private String service;
    private String templateName;
}
