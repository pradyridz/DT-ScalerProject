package com.dtour.userservice.service;

import com.dtour.userservice.modelbuilder.Address;
import com.dtour.userservice.util.UserUtil;
import org.springframework.stereotype.Component;

@Component
public class AddressComponent {

    public com.dtour.userservice.model.Address getAddressFromAddressBuilder(Address addressBuilder) {
        com.dtour.userservice.model.Address address = new com.dtour.userservice.model.Address();
        UserUtil.buildBaseData(address);
        address.setAddressLine1(addressBuilder.getAddressLine1());
        address.setAddressLine2(addressBuilder.getAddressLine2());
        address.setAddressLine3(addressBuilder.getAddressLine3());
        address.setCity(addressBuilder.getCity());
        address.setState(addressBuilder.getState());
        address.setDistrict(addressBuilder.getDistrict());
        address.setCountry(addressBuilder.getCountry());
        address.setPostalCode(addressBuilder.getPostalCode());
        return address;
    }
}
