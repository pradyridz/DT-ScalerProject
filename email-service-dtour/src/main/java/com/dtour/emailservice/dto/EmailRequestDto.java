package com.dtour.emailservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class EmailRequestDto {
    @NotNull
    @NotEmpty
    private String mailTo;
    @NotNull
    @NotEmpty
    private String receiverName;
    private UUID receiverUserId;
    @NotNull
    @NotEmpty
    private String mailFrom;
    private String mailSubject;
    private String mailBody;
    private String eventName;
    private UUID senderUserId;
    private String serviceName;
    private List<MultipartFile> attachments;
}
