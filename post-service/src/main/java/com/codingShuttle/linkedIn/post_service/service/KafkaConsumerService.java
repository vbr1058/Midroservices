package com.codingShuttle.linkedIn.post_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.service
 * @since 27/10/2024 - 03:22 pm
 */
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "VinaySpringBootTopic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

}
