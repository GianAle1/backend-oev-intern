package com.unmsm.oevbackend.controller;

import com.unmsm.oevbackend.dto.response.record.PresignedUrlDTO;
import com.unmsm.oevbackend.service.interfaces.IS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class AmazonRestController {

    private final IS3Service s3Service;


    @PostMapping("/bucket/{bucketName}")
    public ResponseEntity<String> createBucket(@PathVariable String bucketName) {
        String location = s3Service.createBucket(bucketName);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @GetMapping("/check/{bucketName}")
    public ResponseEntity<Boolean> checkIfBucketExists(@PathVariable String bucketName) {
        Boolean exists = s3Service.checkIfBucketExists(bucketName);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/buckets")
    public ResponseEntity<List<String>> getAllBuckets() {
        List<String> buckets = s3Service.getAllBuckets();
        return ResponseEntity.ok(buckets);
    }

    @DeleteMapping("/file/delete")
    public ResponseEntity<Void> deleteFile(@RequestParam String bucketName, @RequestParam String key) {
        s3Service.deleteFile(bucketName, key);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/file/upload")
    public ResponseEntity<Boolean> uploadFile(@RequestParam String bucketName, @RequestParam String key, @RequestPart("file") MultipartFile file) throws IOException {
        Boolean success = s3Service.uploadFile(bucketName, key, file);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping("/file/upload-url")
    public ResponseEntity<PresignedUrlDTO> generatePreSignedUploadUrl(@RequestParam String bucketName, @RequestParam String key, @RequestParam Long durationSeconds) {
        PresignedUrlDTO presignedUrlDTO = s3Service.generatePreSignedUploadUrl(bucketName, key, Duration.ofSeconds(durationSeconds));
        return new ResponseEntity<>(presignedUrlDTO, HttpStatus.OK);
    }

    @GetMapping("/file/download-url")
    public ResponseEntity<PresignedUrlDTO> generatePreSignedDownloadUrl(@RequestParam String bucketName, @RequestParam String key, @RequestParam Long durationSeconds) {
        PresignedUrlDTO presignedUrlDTO = s3Service.generatePreSignedDownloadUrl(bucketName, key, Duration.ofSeconds(durationSeconds));
        return new ResponseEntity<>(presignedUrlDTO, HttpStatus.OK);
    }
}
