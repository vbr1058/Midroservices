package com.codingShuttle.linkedIn.connections_service.service;

import com.codingShuttle.linkedIn.connections_service.auth.UserContextHolder;
import com.codingShuttle.linkedIn.connections_service.entity.Person;
import com.codingShuttle.linkedIn.connections_service.event.AcceptConnectionRequestEvent;
import com.codingShuttle.linkedIn.connections_service.event.SentConnectionRequestEvent;
import com.codingShuttle.linkedIn.connections_service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.connections_service.service
 * @since 07/10/2024 - 11:38 pm
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionsService {


    private final PersonRepository personRepository;

    private final KafkaTemplate<String,String> sentRequestKafkaTemplate;
    private final KafkaTemplate<String,String> acceptRequestKafkaTemplate;


    public List<Person> getAllFirstDegreeConnection(Long userId){
        log.info("Getting First Degree connection for user with the userID: "+userId);
        return personRepository.getFirstDegreeConnections(userId);
    }

    public Boolean sendConnectionRequest(Long receiverId) {
        Long senderId= UserContextHolder.getCurrentUserId();
        log.info("Trying to send the connection request from sender id {} to receiver id{}",senderId,receiverId);

        if (senderId.equals(receiverId)){
            throw new RuntimeException("Sender id and receiver id should not be same");
        }
        boolean alreadySentRequest=personRepository.connectionRequestExists(senderId,receiverId);
        if (alreadySentRequest){
        throw new RuntimeException("Connection request already present");
        }
        boolean alreadyConnected=personRepository.alreadyConnected(senderId,receiverId);
        if (alreadyConnected){
            throw new RuntimeException("Connection already present");
        }
        log.info("Successfully sent the connection request");
        personRepository.addConnectionRequest(senderId,receiverId);
        log.info("Pushing the connection request to kafka topic 'send-connection-request-topic'");
        SentConnectionRequestEvent sentConnectionRequestEvent=SentConnectionRequestEvent.builder()
                .senderId(senderId)
                .receiverId(receiverId).build();
        sentRequestKafkaTemplate.send("send-connection-request-topic",sentConnectionRequestEvent.toString());
        return true;
    }

    public Boolean acceptConnectionRequest(Long senderId) {
        Long receiverId=UserContextHolder.getCurrentUserId();

        boolean connectionRequestExists=personRepository.connectionRequestExists(senderId,receiverId);
        if (!connectionRequestExists){
            throw new RuntimeException("No connection request is present to accept:");
        }
        personRepository.acceptConnectionRequest(senderId,receiverId);
        log.info("Successfully accepted the connection request with sender id {} and receiver id {}",senderId,receiverId);

        log.info("Pushing the accept connection's to kafka topic 'accept-connection-request-topic'");
        AcceptConnectionRequestEvent acceptConnectionRequestEvent=AcceptConnectionRequestEvent.builder()
                .senderId(senderId)
                .receiverId(receiverId).build();
        sentRequestKafkaTemplate.send("accept-connection-request-topic",acceptConnectionRequestEvent.toString());

        return true;
    }

    public Boolean rejectConnectionRequest(Long senderId) {
        Long receiverId=UserContextHolder.getCurrentUserId();
        boolean connectionRequestExists=personRepository.connectionRequestExists(senderId,receiverId);
        if (!connectionRequestExists){
            throw new RuntimeException("No Connection request exists cannot delete or reject the request.");
        }
        log.info("Connection request is being rejected for the sender id {} and receiver Id {}",senderId,receiverId);
        personRepository.rejectConnectionRequest(senderId,receiverId);
        return true;
    }

    public Person createUser(Person person) {
        return personRepository.save(person);
    }
}
