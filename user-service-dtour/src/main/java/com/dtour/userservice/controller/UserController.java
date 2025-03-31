package com.dtour.userservice.controller;

import com.dtour.userservice.dto.AddressDto;
import com.dtour.userservice.dto.AgentRegisterRequestDto;
import com.dtour.userservice.exception.RequiredFieldNullException;
import com.dtour.userservice.modelbuilder.Address;
import com.dtour.userservice.modelbuilder.Agent;
import com.dtour.userservice.modelbuilder.Company;
import com.dtour.userservice.service.IUserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@OpenAPIDefinition
public class UserController {

    IUserService _userService;

    @Autowired
    public UserController(IUserService userService) {
        this._userService = userService;
    }

    @PostMapping(value = "/agent/register")
    @Operation(description = "Method registers a new agent for the business.")
    public ResponseEntity<Void> registerAgent(@Valid @ModelAttribute AgentRegisterRequestDto requestDto) throws Exception {

        //build company and address from request dto
        Address companyAddress = buildAddressFromDto(requestDto.getCompany().getAddress());
        Address agentPermanentAddress = buildAddressFromDto(requestDto.getPermanentAddress());

        Company company = Company.builder()
                .withTravelAgencyNumber(requestDto.getCompany().getTravelAgencyNumber())
                .withPanNumber(requestDto.getCompany().getPanNumber())
                .withWebsiteUrl(requestDto.getCompany().getWebsiteUrl())
                .withAddress(companyAddress)
                .build();

        Agent.Builder agentBuilder = Agent.builder().withPassword(requestDto.getPassword())
                .withFirstName(requestDto.getFirstName())
                .withLastName(requestDto.getLastName())
                .withMobileNumber(requestDto.getMobileNumber())
                .withTelephoneNumber(requestDto.getTelephoneNumber())
                .withEmailAddress(requestDto.getEmailAddress())
                .withPermanentAddress(agentPermanentAddress)
                .withCurrSameAsPermanentAddress(requestDto.isCurrSameAsPermanentAddress())
                .withCompany(company);

        // If agent current address is same as permanent address then no need to build with current address
        // else we will build the agent by providing the current address as well
        Agent agent = requestDto.isCurrSameAsPermanentAddress() ? agentBuilder.build()
                : agentBuilder.withCurrentAddress(buildAddressFromDto(requestDto.getCurrentAddress())).build();
        //register new agent
        _userService.registerNewAgent(agent,requestDto.
                getCompany().getPanImage(), requestDto.getCompany().getGstImage());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/verify-email")
    @Operation(description = "Method is to activate a agent which is newly registered")
    public ResponseEntity<Void> verifyUserEmailId(@RequestParam String uid) {
        _userService.verifyEmail(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Address buildAddressFromDto(AddressDto addressDto) throws RequiredFieldNullException {
        return Address.builder()
                .withAddresLine1(addressDto.getAddresLine1())
                .withAddressLine2(addressDto.getAddressLine2())
                .withAddressLine3(addressDto.getAddressLine3())
                .withCity(addressDto.getCity())
                .withDistrict(addressDto.getDistrict())
                .withState(addressDto.getState())
                .withCountry(addressDto.getCountry())
                .withPostalCode(addressDto.getPostalCode())
                .build();
    }
}
