package com.codingproblem.codingproblem.DemoSpringRest;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AmqpAdminConfig {

    private AmqpAdmin amqpAdmin;

    AmqpAdminConfig(AmqpAdmin amqpAdmin) {this.amqpAdmin = amqpAdmin;}


    @PostConstruct
    public void createQueues() {
        System.out.println("Test Sumit");
        Queue newQueue = new Queue(Constant.TEST_QUEUE,true);
        amqpAdmin.declareQueue(newQueue);
        amqpAdmin.declareBinding(new Binding(newQueue.getName(), Binding.DestinationType.QUEUE,"direct-exchange","routing-key-test",null));
    }
}
