package com.hexagonal.demo.adapter.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.hexagonal.demo.adapter.jpa")
@EnableJpaRepositories("com.hexagonal.demo.adapter.jpa")
public class JpaConfig {
}