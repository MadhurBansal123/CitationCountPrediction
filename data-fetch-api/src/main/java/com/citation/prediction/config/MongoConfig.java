package com.citation.prediction.config;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.citation.prediction.repository")
public class MongoConfig {
}
