package demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories //Allow Spring to Generate Mongo Repositories
class Config {
    var kafkaTopic = "demo2"
    var kafkaHost = "localhost:9092"
}