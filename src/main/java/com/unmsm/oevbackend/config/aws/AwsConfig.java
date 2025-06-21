package com.unmsm.oevbackend.config.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
public class AwsConfig {


    String accessKey = System.getenv("AWS_ACCESS_KEY_ID");
    String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
    String region = System.getenv("AWS_REGION");

    //    Cliente sincrono
    @Bean
    public S3Client s3Client() {
        // Configurar las credenciales de acceso
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Crear el cliente S3
        return S3Client.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create("https://s3." + region + ".amazonaws.com"))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }

    //    Cliente asincrono
    @Bean
    public S3AsyncClient s3AsyncClient() {
        // Configurar las credenciales de acceso
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Crear el cliente S3
        return S3AsyncClient.builder()
                .region(Region.of(region))
//                .endpointOverride(URI.create("https://s3." + region + ".amazonaws.com"))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        // Configurar las credenciales de acceso
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Crear el presigner
        return S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }

}
