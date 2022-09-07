package com.hexagonal.demo.adapter.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    public S3Client getS3Client() {
        return new S3Client();
    }

    public static class S3Client {

        public byte[] getObject(String key) {
            return "test file".getBytes();
        }
    }
}