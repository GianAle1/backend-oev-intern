package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.response.record.PresignedUrlDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
@ConditionalOnProperty(name = "aws.s3.enabled", havingValue = "true")

public interface IS3Service {

    String createBucket(String bucketName);

    Boolean checkIfBucketExists(String bucketName);

    List<String> getAllBuckets();

    void deleteFile(String bucketName, String key);

    Boolean uploadFile(String bucketName, String key, MultipartFile file) throws IOException;

    PresignedUrlDTO generatePreSignedUploadUrl(String bucketName, String key, Duration duration);

    PresignedUrlDTO generatePreSignedDownloadUrl(String bucketName, String key, Duration duration);


}
