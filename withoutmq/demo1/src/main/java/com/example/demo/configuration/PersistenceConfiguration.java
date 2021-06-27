package com.example.demo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    @ConfigurationProperties("db")
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}
