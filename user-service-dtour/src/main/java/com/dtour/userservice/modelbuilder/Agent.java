package com.dtour.userservice.modelbuilder;

import com.dtour.userservice.exception.IntegerLengthException;
import com.dtour.userservice.exception.InvalidDataformatException;
import com.dtour.userservice.exception.RequiredFieldNullException;
import com.dtour.userservice.exception.WeakPasswordException;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Agent {

    private String mobileNumber;
    private String telephoneNumber;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private Address permanentAddress;
    private Address currentAddress;
    private boolean currSameAsPermanentAddress;
    private Company company;

    private Agent(Builder builder) {
        this.mobileNumber = builder.mobileNumber;
        this.telephoneNumber = builder.telephoneNumber;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.permanentAddress = builder.permanentAddress;
        this.currentAddress = builder.currentAddress;
        this.currSameAsPermanentAddress = builder.currSameAsPermanentAddress;
        this.company = builder.company;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String mobileNumber;
        private String telephoneNumber;
        private String emailAddress;
        private String password;
        private String firstName;
        private String lastName;
        private Address permanentAddress;
        private Address currentAddress;
        private Company company;
        private boolean currSameAsPermanentAddress;

        private Builder(){}

        public Builder withMobileNumber(String mobileNumber) throws RequiredFieldNullException, IntegerLengthException {
            if (mobileNumber == null || mobileNumber.isEmpty()) {
                throw new RequiredFieldNullException("mobileNumber");
            }
            if (String.valueOf(mobileNumber).length() != 10) {
                throw new IntegerLengthException("mobileNumber", 10);
            }
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Builder withTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public Builder withEmailAddress(String emailAddress) throws InvalidDataformatException {
            final String EMAIL_PATTERN =
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(emailAddress);
            if (!matcher.matches()) {
                throw new InvalidDataformatException(emailAddress);
            }
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withPassword(String password) throws RequiredFieldNullException, WeakPasswordException {
            if (password == null || password.isEmpty()) {
                throw new RequiredFieldNullException("password");
            }
            final String STRONG_PASSWORD_PATTERN =
                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
            final Pattern pattern = Pattern.compile(STRONG_PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password);
            if (!matcher.matches()) {
                throw new WeakPasswordException();
            }
            this.password = password;
            return this;
        }

        public Builder withFirstName(String firstName) throws RequiredFieldNullException {
            if(firstName == null) {
                throw new RequiredFieldNullException("firstName");
            }
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) throws RequiredFieldNullException {
            if(lastName == null) {
                throw new RequiredFieldNullException("lastName");
            }
            this.lastName = lastName;
            return this;
        }

        public Builder withPermanentAddress(Address permanentAddress) throws RequiredFieldNullException {
            if(permanentAddress == null) {
                throw new RequiredFieldNullException("Permanent Address");
            }
            this.permanentAddress = permanentAddress;
            return this;
        }

        public Builder withCurrSameAsPermanentAddress(boolean currSameAsPermanentAddress) {
            this.currSameAsPermanentAddress = currSameAsPermanentAddress;
            return this;
        }

        public Builder withCurrentAddress(Address currentAddress) throws RequiredFieldNullException {
            if(!currSameAsPermanentAddress && currentAddress == null) {
                throw new RequiredFieldNullException("CurrentAddress");
            }
            this.currentAddress = currentAddress;
            return this;
        }

        public Builder withCompany(Company company) throws RequiredFieldNullException {
            if(company == null) {
                throw new RequiredFieldNullException("Company");
            }
            this.company = company;
            return this;
        }

        public boolean validate() {
            return this.firstName != null && this.lastName != null && this.emailAddress != null && this.mobileNumber != null
                    && this.password != null && this.permanentAddress != null && this.company != null
                    && (currSameAsPermanentAddress || currentAddress != null);
        }

        public Agent build() throws RequiredFieldNullException {
            if(validate()) {
                return new Agent(this);
            }
            throw new RequiredFieldNullException("One or more required fields are missing");
        }
    }

}
