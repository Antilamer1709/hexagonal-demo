package com.hexagonal.demo.adapter.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a dummy config that has to simulate a real S3 configuration with a real client.
 * Now it returns just a random mocked data
 */
@Configuration
public class S3Config {

    @Bean
    public S3Client getS3Client() {
        return new S3Client();
    }

    public static class S3Client {

        public byte[] getObject(String key) {
            return ("test file" + key + LocalTime.now().getSecond()).getBytes();
        }

        public List<byte[]> getObjects(String key) {
            List<byte[]> files = new ArrayList<>();

            for (int i = 0; i < new Random().nextInt(5); i++) {
                files.add(("test file" + key + i).getBytes());
            }

            return files;
        }
    }
}