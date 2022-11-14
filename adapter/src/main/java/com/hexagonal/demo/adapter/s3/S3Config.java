package com.hexagonal.demo.adapter.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

/**
 * This is a dummy config that has to simulate a real S3 configuration with a real client.
 * Now it returns just a random mocked data
 */
@Configuration
public class S3Config {

    private static final String TEST_FIRST_FILE = "test first file";
    private static final String TEST_SECOND_FILE = "test second file";
    private static final String TEST_THIRD_FILE = "test third file";

    @Bean
    public S3Client getS3Client() {
        return new S3Client();
    }

    public static class S3Client {

        public byte[] getObject(String key) {
            return (TEST_FIRST_FILE).getBytes();
        }

        public List<byte[]> getObjects(String key) {
            List<byte[]> files = new ArrayList<>();

            files.add(TEST_SECOND_FILE.getBytes());
            files.add(TEST_THIRD_FILE.getBytes());

            return files;
        }
    }
}