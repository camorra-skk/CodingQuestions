package com.codingproblem.codingproblem.DemoSpringRest;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TestWorker {

    @RabbitListener(queues = Constant.TEST_QUEUE)
    public void testWorker(@Payload Object obj) {
        System.out.println("New Worker created" + obj);
    }
}
