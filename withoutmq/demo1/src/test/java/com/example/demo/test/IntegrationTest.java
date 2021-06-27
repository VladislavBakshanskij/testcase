package com.example.demo.test;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

@Testcontainers
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(initializers = {IntegrationTest.ContainerInitializer.class})
@SqlMergeMode(MERGE)
@Sql(scripts = {
        "classpath:db/clean.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public abstract class IntegrationTest {
    private static final String DB_TEST_NAME = "users";
    private static final GenericContainer<?> dbContainer = new GenericContainer<>("postgres:13.3")
            .withExposedPorts(5432)
            .withEnv("POSTGRES_PASSWORD", "test")
            .withEnv("POSTGRES_USER", "test")
            .withEnv("POSTGRES_DB", DB_TEST_NAME);

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    public static class ContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            dbContainer.start();
            final String jdbcUrl = "jdbc:postgresql://localhost:" + dbContainer.getMappedPort(5432) + "/" +
                    DB_TEST_NAME + "?currentSchema=keyauto";
            TestPropertyValues.of("db.jdbcUrl=" + jdbcUrl)
                    .applyTo(configurableApplicationContext.getEnvironment());

            Flyway flyway = Flyway
                    .configure()
                    .locations("classpath:/db/migration/")
                    .dataSource(jdbcUrl, "test", "test")
                    .load();
            flyway.migrate();
        }
    }
}
