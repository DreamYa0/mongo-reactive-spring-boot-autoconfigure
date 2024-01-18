package com.g7.framework.mongo.reactive;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dreamyao
 * @title
 * @date 2018/8/29 下午1:34
 * @since 1.0.0
 */
@AutoConfiguration
@EnableReactiveMongoAuditing
@EnableTransactionManagement
@EnableReactiveMongoRepositories(basePackages = {"com.**.mongo.**"})
public class ReactiveMongoTransactionAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory dbFactory) {
        // 开启 mongodb的事务
        return new ReactiveMongoTransactionManager(dbFactory);
    }
}
