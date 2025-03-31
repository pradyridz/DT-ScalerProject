package com.dtour.userservice.modelbuilder;

import com.dtour.userservice.exception.RequiredFieldNullException;
import com.dtour.userservice.modelbuilder.Address;
import lombok.Getter;

@Getter
public class Company {
    private String travelAgencyNumber;
    private Address address;
    private String websiteUrl;
    private String panNumber;

    private Company(Builder builder) {
        this.travelAgencyNumber = builder.travelAgencyNumber;
        this.address = builder.address;
        this.websiteUrl = builder.websiteUrl;
        this.panNumber = builder.panNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    public static class Builder {

        private String travelAgencyNumber;
        private Address address;
        private String websiteUrl;
        private String panNumber;

        private Builder() {}

        public Builder withTravelAgencyNumber(String travelAgencyNumber) throws RequiredFieldNullException {
            if(travelAgencyNumber == null || travelAgencyNumber.isBlank() || travelAgencyNumber.isEmpty()) {
                throw new RequiredFieldNullException("TravelAgency Number");
            }
            this.travelAgencyNumber = travelAgencyNumber;
            return this;
        }
        public Builder withAddress(Address address) throws RequiredFieldNullException {
            if (address == null) {
                throw new RequiredFieldNullException("Address");
            }
            this.address = address;
            return this;
        }
        public Builder withWebsiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return this;
        }
        public Builder withPanNumber(String panNumber) throws RequiredFieldNullException {
            if (panNumber == null || panNumber.isBlank() || panNumber.isEmpty()) {
                throw new RequiredFieldNullException("PAN Number");
            }
            this.panNumber = panNumber;
            return this;
        }

        public boolean validate() {
            return this.travelAgencyNumber != null && this.address != null && this.panNumber != null;
        }

        public Company build() throws RequiredFieldNullException {
            if(validate()) {
                return new Company(this);
            }
            throw new RequiredFieldNullException("One or more required fields are missing");
        }
    }
}
