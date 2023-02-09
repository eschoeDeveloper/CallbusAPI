package com.callbus.restapi.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
   basePackages = "com.callbus.restapi.domain"
)
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

}
