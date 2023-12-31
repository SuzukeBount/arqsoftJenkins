package com.isep.acme.consumer;

import org.springframework.messaging.Message;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.isep.acme.model.VoteReviewDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class VoteConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteConsumer.class);

    /*
    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void receive(VoteReviewDTO voteDto) {
        LOGGER.info(String.format("Received JSON message -> %s", voteDto.toString()));
    }
    */

    @RabbitListener(queues = "${rabbitmq.review.fetch.queue.name}")
    public void receive() {
        LOGGER.info(String.format("Received fetch request"));
    }
}