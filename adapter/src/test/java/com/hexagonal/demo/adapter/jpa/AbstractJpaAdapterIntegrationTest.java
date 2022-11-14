package com.hexagonal.demo.adapter.jpa;

import com.github.database.rider.spring.api.DBRider;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.testcontainers.utility.DockerImageName.parse;

@TestPropertySource(properties = {"spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml"})
@DBRider
@DataJpaTest
@Testcontainers
@AutoConfigurationPackage
@AutoConfigureTestDatabase(replace = NONE)
public class AbstractJpaAdapterIntegrationTest {

    private static final String IMAGE = "postgres:15.0";
    private static final String DATASOURCE_URL_PROPERTY_NAME = "spring.datasource.url";
    private static final String DATASOURCE_PASSWORD_PROPERTY_NAME = "spring.datasource.password";
    private static final String DATASOURCE_USERNAME_PROPERTY_NAME = "spring.datasource.username";

    public static final PostgreSQLContainer<?> POSTGRE_DB_CONTAINER = new PostgreSQLContainer<>(parse(IMAGE).asCompatibleSubstituteFor("postgres"));

    static {
        POSTGRE_DB_CONTAINER.setCommand("postgres", "-c", "max_connections=20000");
        POSTGRE_DB_CONTAINER.start();
    }

    @SuppressWarnings("unused")
    @DynamicPropertySource
    static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add(DATASOURCE_URL_PROPERTY_NAME, POSTGRE_DB_CONTAINER::getJdbcUrl);
        registry.add(DATASOURCE_PASSWORD_PROPERTY_NAME, POSTGRE_DB_CONTAINER::getPassword);
        registry.add(DATASOURCE_USERNAME_PROPERTY_NAME, POSTGRE_DB_CONTAINER::getUsername);
    }
}