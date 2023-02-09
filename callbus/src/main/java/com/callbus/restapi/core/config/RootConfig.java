package com.callbus.restapi.core.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@Import(value = { DatabaseConfig.class })
public class RootConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean("jpaQueryFactory")
    public JPAQueryFactory jpaQueryFactory() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        return jpaQueryFactory;
    }

}
