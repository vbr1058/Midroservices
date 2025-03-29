package com.codingShuttle.linkedIn.post_service.config;

import org.apache.kafka.common.serialization.LongSerializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.config
 * @since 28/10/2024 - 12:14 am
 */

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic postCreatedTopic() {
        return new NewTopic("post-created-topic", 3, (short) 1); // 3 partitions, replication factor of 1
    }

    @Bean
    public NewTopic postLikedTopic() {
        return new NewTopic("post-liked-topic", 3, (short) 1); // 3 partitions, replication factor of 1
    }
}
