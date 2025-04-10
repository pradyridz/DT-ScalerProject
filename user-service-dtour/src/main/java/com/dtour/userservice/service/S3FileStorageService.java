package com.dtour.userservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.dtour.userservice.exception.S3FileIOException;
import com.dtour.userservice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class S3FileStorageService implements StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public S3FileStorageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(MultipartFile file, User user) throws S3FileIOException {
        try {
            String directory = user.getName() + "/" + user.getId().toString() + "/" + new Date() + "/";
            File fileObj = convertMultiPartFileToFile(file);
            String fileName = file.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(bucketName, directory + fileName, fileObj));
            fileObj.delete();
            return directory + fileName;
        } catch (Exception e) {
            throw new S3FileIOException("File/s upload to storage failed.", e.getCause());
        }
    }

    @Override
    public byte[] downloadFile(String fileName, User user) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteFile(String fileName, User user) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Exception");
        }
        return convertedFile;
    }
}
