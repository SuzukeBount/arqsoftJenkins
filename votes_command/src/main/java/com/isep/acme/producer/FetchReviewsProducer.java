package com.isep.acme.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FetchReviewsProducer {
    
    @Value("${rabbitmq.review.fetch.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.review.fetch.routing.key}")
    private String key;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchReviewsProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendFetchMessage() {
         try{
            rabbitTemplate.convertAndSend(exchange, key);
            LOGGER.info(String.format("Fetch Request Sent"));
        }catch(Exception e){
            LOGGER.error(String.format("Erro ao enviar pedido de reviews " + e.getMessage()));
        }
    }
}
