package com.dtour.userservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Company extends BaseModel {

    private String travelAgencyNumber;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Address address;
    private String websiteUrl;
    private String panNumber;
    private String panImage;
    private String gstImage;
}
