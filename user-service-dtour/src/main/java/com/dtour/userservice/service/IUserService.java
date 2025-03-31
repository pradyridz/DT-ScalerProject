package com.dtour.userservice.service;

import com.dtour.userservice.dto.UesrDto;
import com.dtour.userservice.model.Agent;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {

    public Agent registerNewAgent(com.dtour.userservice.modelbuilder.Agent agent, MultipartFile panImage, MultipartFile gstImage) throws Exception;

    public void verifyEmail(String userId);
}
