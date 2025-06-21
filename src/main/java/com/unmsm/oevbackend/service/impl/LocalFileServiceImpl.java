package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.config.storage.LocalStorageConfig;
import com.unmsm.oevbackend.dto.response.record.PresignedUrlDTO;
import com.unmsm.oevbackend.service.interfaces.IS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "aws.enabled", havingValue = "false", matchIfMissing = true)
public class LocalFileServiceImpl implements IS3Service {

    private final LocalStorageConfig storageConfig;

    @Override
    public String createBucket(String bucketName) {
        try {
            Path bucketPath = Paths.get(storageConfig.getUploadDir(), bucketName);
            Files.createDirectories(bucketPath);
            log.info("Local directory created: {}", bucketPath);
            return "Local directory created: " + bucketPath;
        } catch (IOException e) {
            log.error("Error creating local directory: {}", bucketName, e);
            throw new RuntimeException("Failed to create local directory: " + bucketName, e);
        }
    }

    @Override
    public Boolean checkIfBucketExists(String bucketName) {
        Path bucketPath = Paths.get(storageConfig.getUploadDir(), bucketName);
        return Files.exists(bucketPath) && Files.isDirectory(bucketPath);
    }

    @Override
    public List<String> getAllBuckets() {
        try {
            Path uploadPath = Paths.get(storageConfig.getUploadDir());
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                return List.of();
            }

            return Files.list(uploadPath)
                    .filter(Files::isDirectory)
                    .map(path -> path.getFileName().toString())
                    .toList();
        } catch (IOException e) {
            log.error("Error listing local directories", e);
            return List.of();
        }
    }

    @Override
    public void deleteFile(String bucketName, String key) {
        try {
            Path filePath = Paths.get(storageConfig.getUploadDir(), bucketName, key);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("File deleted locally: {}", filePath);
            } else {
                log.warn("File not found for deletion: {}", filePath);
            }
        } catch (IOException e) {
            log.error("Error deleting local file: {}/{}", bucketName, key, e);
            throw new RuntimeException("Failed to delete local file: " + bucketName + "/" + key, e);
        }
    }

    @Override
    public Boolean uploadFile(String bucketName, String key, MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public PresignedUrlDTO generatePreSignedUploadUrl(String bucketName, String key, Duration duration) {
        return null;
    }

    @Override
    public PresignedUrlDTO generatePreSignedDownloadUrl(String bucketName, String key, Duration duration) {
        return null;
    }

    // Additional methods that might be needed for file operations
    public String uploadFile(String bucketName, MultipartFile file) {
        try {
            // Create bucket directory if it doesn't exist
            createBucket(bucketName);

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : "";
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            // Save file
            Path filePath = Paths.get(storageConfig.getUploadDir(), bucketName, uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            log.info("File uploaded locally: {}", filePath);
            return uniqueFilename; // Return the key/filename
        } catch (IOException e) {
            log.error("Error uploading file locally to bucket: {}", bucketName, e);
            throw new RuntimeException("Failed to upload file locally", e);
        }
    }

    public String getFileUrl(String bucketName, String key) {
        // Return a local file URL or path
        return "file://" + Paths.get(storageConfig.getUploadDir(), bucketName, key).toAbsolutePath();
    }

    public byte[] downloadFile(String bucketName, String key) {
        try {
            Path filePath = Paths.get(storageConfig.getUploadDir(), bucketName, key);
            if (Files.exists(filePath)) {
                return Files.readAllBytes(filePath);
            } else {
                throw new RuntimeException("File not found: " + bucketName + "/" + key);
            }
        } catch (IOException e) {
            log.error("Error downloading file: {}/{}", bucketName, key, e);
            throw new RuntimeException("Failed to download file: " + bucketName + "/" + key, e);
        }
    }

    public boolean fileExists(String bucketName, String key) {
        Path filePath = Paths.get(storageConfig.getUploadDir(), bucketName, key);
        return Files.exists(filePath) && Files.isRegularFile(filePath);
    }
}