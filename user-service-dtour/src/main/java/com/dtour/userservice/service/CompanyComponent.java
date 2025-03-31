package com.dtour.userservice.service;

import com.dtour.userservice.modelbuilder.Company;
import com.dtour.userservice.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyComponent {

    AddressComponent addressComponent;

    @Autowired
    public CompanyComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }
    public com.dtour.userservice.model.Company getCompanyFromCompanyBuilder(Company companyBuilder) {
        com.dtour.userservice.model.Company company = new com.dtour.userservice.model.Company();
        UserUtil.buildBaseData(company);
        company.setTravelAgencyNumber(companyBuilder.getTravelAgencyNumber());
        company.setAddress(addressComponent.getAddressFromAddressBuilder(companyBuilder.getAddress()));
        company.setWebsiteUrl(companyBuilder.getWebsiteUrl());
        company.setPanNumber(companyBuilder.getPanNumber());
        return company;
    }
}
