package com.example.MessageProducer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String publishMessage(String consumptionJSON) {
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, consumptionJSON);
        return "Message published!";
    }
}
