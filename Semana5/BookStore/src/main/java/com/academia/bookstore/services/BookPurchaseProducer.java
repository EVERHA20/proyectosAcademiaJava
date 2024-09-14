package com.academia.bookstore.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.academia.bookstore.dto.BookPurchaseMessage;

@Service
public class BookPurchaseProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.booksExchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key.booksRoutingKey}")
    private String routingKey;

    public void sendBookPurchaseMessage(BookPurchaseMessage message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Sent message: " + message);
    }
}
