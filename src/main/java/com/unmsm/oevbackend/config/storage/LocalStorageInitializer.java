package com.unmsm.oevbackend.config.storage;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "aws.enabled", havingValue = "false", matchIfMissing = true)
public class LocalStorageInitializer {

    private final LocalStorageConfig storageConfig;

    @PostConstruct
    public void initializeDirectories() {
        try {
            // Create main upload directory
            createDirectoryIfNotExists(storageConfig.getUploadDir());

            // Create video directory
            createDirectoryIfNotExists(storageConfig.getVideoDir());

            // Create image directory
            createDirectoryIfNotExists(storageConfig.getImageDir());

            log.info("Local storage directories initialized successfully");
        } catch (IOException e) {
            log.error("Failed to initialize local storage directories", e);
            throw new RuntimeException("Failed to initialize local storage directories", e);
        }
    }

    private void createDirectoryIfNotExists(String directory) throws IOException {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            log.info("Created directory: {}", path.toAbsolutePath());
        } else {
            log.info("Directory already exists: {}", path.toAbsolutePath());
        }
    }
}
