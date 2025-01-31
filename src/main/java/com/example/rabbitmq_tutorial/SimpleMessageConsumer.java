package com.example.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class SimpleMessageConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "energy-utility")
    public void consumeMessage(String consumptionJSON) {
        try {
            // Deserialize JSON to ConsumptionJSON object
            ConsumptionMessage consumption = objectMapper.readValue(consumptionJSON, ConsumptionMessage.class);

            // Print the received message
            System.out.println("Received Consumption Data:");
            System.out.println("Date: " + consumption.getDate());
            System.out.println("Time: " + consumption.getTime());
            System.out.println("Energy Consumption: " + consumption.getEnergyConsumption());
            System.out.println("Metering Device ID: " + consumption.getMeteringDeviceId());
            System.out.println("--------------------------------------------------");
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse message: " + e.getMessage());
        }
    }
}
