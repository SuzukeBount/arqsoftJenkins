package com.isep.acme;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.upvote.queue.name}")
    private String upvotedQueue;

    @Value("${rabbitmq.downvote.queue.name}")
    private String downvotedQueue;

    @Value("${rabbitmq.exchange.name}")
    private String votesExchange;

    @Value("${rabbitmq.upvote.routing.key}")
    private String upvoteRoutingKey;

    @Value("${rabbitmq.downvote.routing.key}")
    private String downvoteRoutingKey;

    @Bean
    public Queue upvotedQueue() {
        return new Queue(upvotedQueue);
    }

    @Bean Queue downvotedQueue() {
        return new Queue(downvotedQueue);
    }

    @Bean
    public TopicExchange votesExchange() {
        return new TopicExchange(votesExchange);
    }

    @Bean
    public Binding upvoteBinding() {
        return BindingBuilder
        .bind(upvotedQueue())
        .to(votesExchange())
        .with(upvoteRoutingKey);
    }

    @Bean
    public Binding downvoteBinding() {
        return BindingBuilder
        .bind(downvotedQueue())
        .to(votesExchange())
        .with(downvoteRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    } 
}
