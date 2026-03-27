package com.example.demo.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue sourceQueue() {
        // durable queue named "source_topic"
        return new Queue("source_topic", true);
    }
}