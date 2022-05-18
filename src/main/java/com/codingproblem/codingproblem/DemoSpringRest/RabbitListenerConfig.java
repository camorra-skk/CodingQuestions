package com.codingproblem.codingproblem.DemoSpringRest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.util.List;
import java.util.stream.Collectors;

@EnableRabbit
@Configuration
public class RabbitListenerConfig implements RabbitListenerConfigurer {


    @Autowired
    ObjectMapper objectMapper;




    @Bean("autoAcknowledgeModeSerialRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory autoAcknowledgeModeSerialRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        MappingJackson2MessageConverter jackson2MessageConverter = new MappingJackson2MessageConverter();
        jackson2MessageConverter.setObjectMapper(objectMapper);
        return jackson2MessageConverter;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(mappingJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public Declarables testeQueue() {
//        return new Declarables(new Queue(Constant.TEST_QUEUE, true),new Queue(Constant.TEST_QUEUE1,true));
//    }
//
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange("direct-exchange");
//    }
//
//    @Bean
//    List<Binding> testeBinding(Declarables testeQueue, DirectExchange exchange) {
//        return testeQueue.getDeclarables().stream().map(x -> BindingBuilder.bind((Queue) x).to(exchange)
//                .with("xxx1-teste-routing-key")).collect(Collectors.toList());
////        return BindingBuilder.bind(testeQueue).to(exchange).with("xxx1-teste-routing-key");
//    }
}
