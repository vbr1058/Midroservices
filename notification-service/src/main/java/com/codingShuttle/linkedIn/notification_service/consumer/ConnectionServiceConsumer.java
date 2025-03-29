package com.codingShuttle.linkedIn.notification_service.consumer;

import com.codingShuttle.linkedIn.connection_service.event.AcceptConnectionRequestEvent;
import com.codingShuttle.linkedIn.connection_service.event.SentConnectionRequestEvent;
import com.codingShuttle.linkedIn.notification_service.service.SendNotification;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.notification_service.consumer
 * @since 31/10/2024 - 11:40 pm
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionServiceConsumer {

    private final SendNotification sendNotification;

    @Autowired
    private ModelMapper modelMapper;

    public ConnectionServiceConsumer(SendNotification sendNotification) {
        this.sendNotification = sendNotification;
    }

    @KafkaListener(topics = "send-connection-request-topic")
    public void handelSendConnectionRequest(String connectionRequestString){
        String message="You have received a connection with the details: "+connectionRequestString;
        System.out.println(message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SentConnectionRequestEvent sentConnectionRequestEvent = objectMapper.readValue(connectionRequestString, SentConnectionRequestEvent.class);
            sendNotification.send(sentConnectionRequestEvent.getSenderId(), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @KafkaListener(topics = "accept-connection-request-topic")
    public void handelAcceptConnectionRequest(String acceptConnectionRequestString){
        String message="Your connection Request was accepted by userId: %d"+acceptConnectionRequestString;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AcceptConnectionRequestEvent acceptConnectionRequestEvent = objectMapper.readValue(acceptConnectionRequestString, AcceptConnectionRequestEvent.class);
            sendNotification.send(acceptConnectionRequestEvent.getSenderId(),message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
