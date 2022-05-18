package com.codingproblem.codingproblem.DemoSpringRest;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestAPIClass {

    public TestAPIClass(RabbitTemplate queueSender) {
        this.queueSender = queueSender;
    }

    private final RabbitTemplate queueSender;

    @GetMapping
    public String testAPI() {
        System.out.println("Inside test");
        queueSender.convertAndSend("direct-exchange","routing-key-test","test Message");
        return "ok. done";
    }
}
