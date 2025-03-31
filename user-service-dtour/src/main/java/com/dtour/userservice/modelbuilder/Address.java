package com.dtour.userservice.modelbuilder;

import com.dtour.userservice.exception.RequiredFieldNullException;
import lombok.Getter;

@Getter
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String district;
    private String state;
    private String country;
    private String postalCode;

    private Address(Builder builder){
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.addressLine3 = builder.addressLine3;
        this.city = builder.city;
        this.district = builder.district;
        this.state = builder.state;
        this.country = builder.country;
        this.postalCode = builder.postalCode;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String city;
        private String district;
        private String state;
        private String country;
        private String postalCode;

        private Builder(){}

        public Builder withAddresLine1(String addressLine1) throws RequiredFieldNullException {
            if(addressLine1 == null || addressLine1.isEmpty() || addressLine1.isBlank()) {
                throw new RequiredFieldNullException("AddressLine1");
            }
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder withAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder withAddressLine3(String addressLine3) {
            this.addressLine3 = addressLine3;
            return this;
        }

        public Builder withCity(String city) throws RequiredFieldNullException {
            if(city == null || city.isEmpty() || city.isBlank()) {
                throw new RequiredFieldNullException("City");
            }
            this.city = city;
            return this;
        }

        public Builder withState(String state) throws RequiredFieldNullException {
            if(state == null || state.isEmpty() || state.isBlank()) {
                throw new RequiredFieldNullException("State");
            }
            this.state = state;
            return this;
        }
        public Builder withDistrict(String district) throws RequiredFieldNullException {
            if(district == null || district.isEmpty() || district.isBlank()) {
                throw new RequiredFieldNullException("District");
            }
            this.district = district;
            return this;
        }
        public Builder withCountry(String country) throws RequiredFieldNullException {
            if(country == null || country.isEmpty() || country.isBlank()) {
                throw new RequiredFieldNullException("Country");
            }
            this.country = country;
            return this;
        }

        public Builder withPostalCode(String postalCode) throws RequiredFieldNullException {
            if(postalCode == null || postalCode.isEmpty() || postalCode.isBlank()) {
                throw new RequiredFieldNullException("PostalCode");
            }
            this.postalCode = postalCode;
            return this;
        }

        public boolean validate() {
            return this.addressLine1 != null && this.city != null && this.state != null
                    && this.country != null && this.postalCode != null && this.district != null;
        }

        public Address build() throws RequiredFieldNullException {
            if(validate()) {
                return new Address(this);
            }
            throw new RequiredFieldNullException("One or more required fields are missing");
        }
    }
}
