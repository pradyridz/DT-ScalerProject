package com.dtour.userservice.service;

import com.dtour.userservice.exception.S3FileIOException;
import com.dtour.userservice.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    public String uploadFile(MultipartFile file, User user) throws S3FileIOException;

    public byte[] downloadFile(String fileName, User user);

    public String deleteFile(String fileName, User user);


}
