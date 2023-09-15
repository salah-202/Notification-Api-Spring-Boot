package com.ecommerce.notification.configurations;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitMQ.queue.name}")
    private String queue;
    @Value("${rabbitMQ.exchange.name}")
    private String exchange;
    @Value("${rabbitMQ.routing.key}")
    private String key;
    @Bean
    public Queue notificationQueue(){
        return new Queue(queue);
    }
    @Bean
    public DirectExchange notificationExchange(){
        return new DirectExchange(exchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(notificationQueue()).to(notificationExchange()).with(key);
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
