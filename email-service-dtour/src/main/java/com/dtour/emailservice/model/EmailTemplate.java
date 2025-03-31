package com.dtour.emailservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EmailTemplate extends BaseModel{
    private String emailSubject;
    private String emailTemplateName;
    private String eventName;
}
