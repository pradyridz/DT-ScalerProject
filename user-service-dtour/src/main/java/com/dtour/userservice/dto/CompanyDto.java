package com.dtour.userservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CompanyDto {

    private String travelAgencyNumber;
    private AddressDto address;
    private String websiteUrl;
    private String panNumber;
    @NotNull(message = "Pan Image is mandatory to be uploaded")
    @NotEmpty(message = "Pan Image file cannot be empty and is mandatory to be uploaded")
    private MultipartFile panImage;

    @NotNull(message = "GST Image is mandatory to be uploaded")
    @NotEmpty(message = "GST Image file cannot be empty and is mandatory to be uploaded")
    private MultipartFile gstImage;
}
