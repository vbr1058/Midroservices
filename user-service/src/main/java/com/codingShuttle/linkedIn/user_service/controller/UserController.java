package com.codingShuttle.linkedIn.user_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.controller
 * @since 20/10/2024 - 11:58 pm
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public UserController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message){
        kafkaTemplate.send("VinaySpringBootTopic",message);
        return ResponseEntity.ok("Message is queued");
    }
}
