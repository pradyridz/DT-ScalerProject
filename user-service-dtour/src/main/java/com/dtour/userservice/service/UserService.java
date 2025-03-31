package com.dtour.userservice.service;

import com.dtour.userservice.exception.EmailNotificationException;
import com.dtour.userservice.exception.S3FileIOException;
import com.dtour.userservice.model.Address;
import com.dtour.userservice.model.Agent;
import com.dtour.userservice.model.Company;
import com.dtour.userservice.model.UserRole;
import com.dtour.userservice.repo.AgentRepository;
import com.dtour.userservice.repo.UserRepository;
import com.dtour.userservice.repo.UserRoleRepository;
import com.dtour.userservice.repo.UserStatusRepository;
import com.dtour.userservice.typelist.UserRoleEnum;
import com.dtour.userservice.typelist.UserStatusEnum;
import com.nimbusds.oauth2.sdk.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class UserService implements IUserService {

    private final CompanyComponent companyComponent;
    private final AddressComponent addressComponent;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final UserStatusRepository userStatusRepository;
    private final AgentRepository agentRepository;
    private final S3FileStorageService s3StorageService;
    private final EmailService emailService;
    private final UserRepository userRepository;


    @Autowired
    public UserService(CompanyComponent companyComponent,
                       AddressComponent addressComponent,
                       BCryptPasswordEncoder passwordEncoder,
                       UserRoleRepository userRoleRepository,
                       UserStatusRepository userStatusRepository,
                       AgentRepository agentRepository,
                       S3FileStorageService s3StorageService,
                       EmailService emailService,
                       UserRepository userRepository) {
        this.companyComponent = companyComponent;
        this.addressComponent = addressComponent;
        this.bCryptPasswordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.userStatusRepository = userStatusRepository;
        this.agentRepository = agentRepository;
        this.s3StorageService = s3StorageService;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @Override
    public Agent registerNewAgent(com.dtour.userservice.modelbuilder.Agent agentbuilder, MultipartFile panImage, MultipartFile gstImage) throws URISyntaxException, EmailNotificationException, S3FileIOException, IOException, ParseException {
        Agent agent = mapAgentData(agentbuilder);

        //check if user with same email and mobile exist
        Agent savedAgent = agentRepository.save(agent);
        savedAgent.getCompany().setGstImage(s3StorageService.uploadFile(gstImage, savedAgent));
        savedAgent.getCompany().setPanImage(s3StorageService.uploadFile(panImage, savedAgent));
        agentRepository.save(savedAgent);
        LinkedMultiValueMap<String, Object> emailFormData = buildAgentRegistrationEmailRequest(savedAgent);
        ResponseEntity<String> emailResponse = emailService.sendRequestToEmailService(emailFormData);
        if (emailResponse.getStatusCode() == HttpStatusCode.valueOf(400)
                || emailResponse.getStatusCode() == HttpStatusCode.valueOf(500)) {
            //log the issue
            throw new EmailNotificationException();
        }
        return savedAgent;
    }

    @Override
    public void verifyEmail(String userId) {
        Optional<Agent> agentOptional = agentRepository.findById(UUID.fromString(userId));
        if(agentOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with id "+userId+" doesnot exist");
        }
        Agent agent = agentOptional.get();
        agent.setEmailVerified(true);
        agent.setUpdatedAt(new Date());
        agentRepository.save(agent);
    }

    private Agent mapAgentData(com.dtour.userservice.modelbuilder.Agent agent) {
        Address permanentAddressOfAgent = addressComponent.getAddressFromAddressBuilder(agent.getPermanentAddress());
        Address currentAddressOfAgent = addressComponent.getAddressFromAddressBuilder(agent.getCurrentAddress());
        Company companyOfAgent = companyComponent.getCompanyFromCompanyBuilder(agent.getCompany());
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRoleRepository.getUserRoleByCode(UserRoleEnum.AGENT.getValue()).get());
        Agent dbAgent = new Agent();
        dbAgent.setFirstName(agent.getFirstName());
        dbAgent.setLastName(agent.getLastName());
        dbAgent.setEmailAddress(agent.getEmailAddress());
        dbAgent.setMobileNumber(agent.getMobileNumber());
        dbAgent.setTelephoneNumber(agent.getTelephoneNumber());
        dbAgent.setEncryptedPassword(bCryptPasswordEncoder.encode(agent.getPassword()));
        dbAgent.setName(agent.getFirstName() + " " + agent.getLastName());
        dbAgent.setCreatedAt(new Date());
        dbAgent.setUpdatedAt(new Date());
        dbAgent.setEmailVerified(false);
        dbAgent.setStatus(userStatusRepository.getUserStatusByCode(UserStatusEnum.INACTIVE.getValue()));
        dbAgent.setRoles(roles);
        dbAgent.setCurrSameAsPermanentAddress(agent.isCurrSameAsPermanentAddress());
        dbAgent.setPermanentAddress(permanentAddressOfAgent);
        dbAgent.setCurrentAddress(currentAddressOfAgent);
        dbAgent.setCompany(companyOfAgent);
        dbAgent.setDeleted(false);
        return dbAgent;
    }

    private LinkedMultiValueMap<String, Object> buildAgentRegistrationEmailRequest(Agent agent) {
        LinkedMultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.put("mailTo", List.of(agent.getEmailAddress()));
        formData.put("receiverName", List.of(agent.getName()));
        formData.put("mailFrom", List.of("test.no-reply@dtours.in"));
        formData.put("eventName", List.of("NEW_AGENT_REGISTRATION"));
        formData.put("receiverUserId", List.of(agent.getId()));
        formData.put("senderUserId",userRepository.findUserByRoles(userRoleRepository.getUserRoleByCode(UserRoleEnum.INTEGRATION_USER.getValue()).get()));
        return formData;
    }

}
