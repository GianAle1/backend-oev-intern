package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.response.record.PresignedUrlDTO;
import com.unmsm.oevbackend.service.interfaces.IS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements IS3Service {

    private final S3Client s3Client;

    private final S3Presigner s3Presigner;


    @Override
    public String createBucket(String bucketName) {
        return this.s3Client.createBucket(bucketBuilder -> bucketBuilder.bucket(bucketName)).location();
    }

    @Override
    public Boolean checkIfBucketExists(String bucketName) {
        try {
            this.s3Client.headBucket(headBucketRequest -> headBucketRequest.bucket(bucketName));
            return true;
        } catch (S3Exception exception) {
            return false;
        }
    }

    @Override
    public List<String> getAllBuckets() {
        ListBucketsResponse listBucketsResponse = this.s3Client.listBuckets();
        if (listBucketsResponse.hasBuckets()) {
            return listBucketsResponse.buckets().stream().map(Bucket::name).toList();
        }
        return List.of();
    }

    @Override
    public void deleteFile(String bucketName, String key) {
        this.s3Client.deleteObject(deleteObjectRequest -> deleteObjectRequest.bucket(bucketName).key(key));
    }

    @Override
    public Boolean uploadFile(String bucketName, String key, MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            PutObjectResponse putObjectResponse = this.s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromBytes(file.getBytes())
            );

            return putObjectResponse.sdkHttpResponse().isSuccessful();
        } catch (IOException e) {
            // Loguear la excepción para depuración
            log.error("Error al leer el archivo: {}", e.getMessage(), e);
        } catch (S3Exception e) {
            // Loguear errores específicos de AWS S3
            log.error("Error al subir el archivo a S3: {}", e.getMessage(), e);
        }

        return false; // Retorna false si hubo un error
    }


    @Override
    public PresignedUrlDTO generatePreSignedUploadUrl(String bucketName, String key, Duration duration) {
        try { // Try-With-Resources para cerrar automáticamente
            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(duration)
                    .putObjectRequest(putObjectRequest -> putObjectRequest.bucket(bucketName).key(key))
                    .build();
            String url = s3Presigner.presignPutObject(presignRequest).url().toString();
            log.info("URL generada: {}", url);
            return new PresignedUrlDTO(url);
        } catch (S3Exception e) {
            log.error("Error al generar Presigned URL para S3: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error inesperado al generar Presigned URL: {}", e.getMessage(), e);
        }

        return null;
    }


    @Override
    public PresignedUrlDTO generatePreSignedDownloadUrl(String bucketName, String key, Duration duration) {
        try { // Try-With-Resources para cerrar automáticamente

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(duration)
                    .getObjectRequest(getObjectRequest)
                    .build();

            String url = s3Presigner.presignGetObject(presignRequest).url().toString();
            log.info("URL generada: {}", url);
            return new PresignedUrlDTO(url);

        } catch (S3Exception e) {
            log.error("Error al generar Presigned URL para S3: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error inesperado al generar Presigned URL: {}", e.getMessage(), e);
        }
        return null;
    }
}
