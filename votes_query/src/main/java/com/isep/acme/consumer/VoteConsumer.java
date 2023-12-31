package com.isep.acme.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.repositories.VoteRepository;
import com.isep.acme.services.IReviewService;
import com.isep.acme.services.IVoteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class VoteConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteConsumer.class);

    @Autowired
    private IReviewService reviewService;

    // Subscrever o add review e o delete review

    @RabbitListener(queues = "${rabbitmq.upvote.queue.name}")
    public void upvotedQueue(VoteReviewDTO voteDto) {
        LOGGER.info(String.format("Received JSON message -> %s", voteDto.toString()));

        // reviewService.createReview(voteDto);
    }

    @RabbitListener(queues = "${rabbitmq.downvote.queue.name}")
    public void downvotedQueue(VoteReviewDTO voteDto) {
        LOGGER.info(String.format("Received JSON message -> %s", voteDto.toString()));

        // reviewService.createReview(voteDto);
    }
}