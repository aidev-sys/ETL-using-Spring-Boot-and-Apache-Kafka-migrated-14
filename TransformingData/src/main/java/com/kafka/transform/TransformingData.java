package com.kafka.transform;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransformingData {

    private final RabbitTemplate rabbitTemplate;

    public TransformingData(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(TransformingData.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            System.out.println("TransformingData application started.");
        };
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "source_queue"))
    public void handleMessage(String message) {
        String result = message;
        String[] fileData = result.split(",");
        int res = 0;
        String operator = fileData[2];

        switch (operator) {
            case "+":
                res = Integer.parseInt(fileData[0]) + Integer.parseInt(fileData[1]);
                break;
            case "-":
                res = Integer.parseInt(fileData[0]) - Integer.parseInt(fileData[1]);
                break;
            case "*":
                res = Integer.parseInt(fileData[0]) * Integer.parseInt(fileData[1]);
                break;
            case "/":
                res = Integer.parseInt(fileData[0]) / Integer.parseInt(fileData[1]);
                break;
            default:
                // Unknown operator; keep original result
                break;
        }
        result = message + "," + res;
        rabbitTemplate.convertAndSend("target_queue", result);
    }
}