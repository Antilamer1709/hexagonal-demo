package com.hexagonal.demo.adapter.jpa;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {"spring.jpa.defer-datasource-initialization=true"})
@DataJpaTest
@AutoConfigurationPackage
public class AbstractAdapterIntegrationTest {
    
}