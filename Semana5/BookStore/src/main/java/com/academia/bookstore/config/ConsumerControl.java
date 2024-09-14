package com.academia.bookstore.config;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerControl {

    @Autowired
    private SimpleMessageListenerContainer messageListenerContainer;

    public void stopConsumer() {
        messageListenerContainer.stop();
    }

    public void startConsumer() {
        messageListenerContainer.start();
    }
}