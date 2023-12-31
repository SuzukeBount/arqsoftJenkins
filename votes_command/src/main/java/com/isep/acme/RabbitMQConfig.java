package com.isep.acme;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /* Queues */
    @Value("${rabbitmq.upvote.queue.name}")
    private String upvotedQueue;

    @Value("${rabbitmq.downvote.queue.name}")
    private String downvotedQueue;

    @Value("${rabbitmq.review.fetch.queue.name}")
    private String fetchQueue;

    @Value("${rabbitmq.review.response.queue.name}")
    private String fetchResponseQueue;

    @Bean
    public Queue upvotedQueue() {
        return new Queue(upvotedQueue);
    }

    @Bean Queue downvotedQueue() {
        return new Queue(downvotedQueue);
    }

    @Bean Queue fetchQueue() {
        return new Queue(fetchQueue);
    }

    @Bean Queue fetchResponseQueue() {
        return new Queue(fetchResponseQueue);
    }

    /* Exchanges */
    @Value("${rabbitmq.upvote.exchange.name}")
    private String upvotesExchange;

    @Value("${rabbitmq.downvote.exchange.name}")
    private String downvotesExchange;

    @Value("${rabbitmq.review.fetch.exchange.name}")
    private String fetchVotesExchange;

    @Value("${rabbitmq.review.response.exchange.name}")
    private String fetchResponseVotesExchange;

    @Bean
    public TopicExchange upvotesExchange() {
        return new TopicExchange(upvotesExchange);
    }

    @Bean
    public TopicExchange downvotesExchange() {
        return new TopicExchange(downvotesExchange);
    }

    @Bean
    public TopicExchange fetchVotesExchange() {
        return new TopicExchange(fetchVotesExchange);
    }

    @Bean
    public TopicExchange fetchResponseVotesExchange() {
        return new TopicExchange(fetchResponseVotesExchange);
    }

    /* Keys */
    @Value("${rabbitmq.upvote.routing.key}")
    private String upvoteRoutingKey;

    @Value("${rabbitmq.downvote.routing.key}")
    private String downvoteRoutingKey;

    @Value("${rabbitmq.review.fetch.routing.key}")
    private String fetchRoutingKey;

    @Value("${rabbitmq.review.response.routing.key}")
    private String fetchResponseRoutingKey;

    @Bean
    public Binding upvoteBinding() {
        return BindingBuilder
        .bind(upvotedQueue())
        .to(upvotesExchange())
        .with(upvoteRoutingKey);
    }

    @Bean
    public Binding downvoteBinding() {
        return BindingBuilder
        .bind(downvotedQueue())
        .to(downvotesExchange())
        .with(downvoteRoutingKey);
    }

    @Bean
    public Binding fetchReviewBinding() {
        return BindingBuilder
        .bind(fetchQueue())
        .to(fetchVotesExchange())
        .with(fetchRoutingKey);
    }

    @Bean
    public Binding fetchResponseReviewBinding() {
        return BindingBuilder
        .bind(fetchResponseQueue())
        .to(fetchResponseVotesExchange())
        .with(fetchResponseRoutingKey);
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
