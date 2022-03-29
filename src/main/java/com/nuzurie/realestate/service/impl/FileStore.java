package com.nuzurie.realestate.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.nuzurie.realestate.bucket.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class FileStore {

    private final AmazonS3 s3;

    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> optional,
                     InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optional.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            s3.putObject(path, fileName, inputStream, objectMetadata);
        }
        catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to save image " + e.getErrorMessage());
        }
    }

    public String getUrl(String fullPath) {
        return s3.getUrl(Bucket.LISTING_IMAGE.getBucketName(), fullPath).toString();
    }

}
