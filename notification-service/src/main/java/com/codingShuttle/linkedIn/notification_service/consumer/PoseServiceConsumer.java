package com.codingShuttle.linkedIn.notification_service.consumer;

import com.codingShuttle.linkedIn.notification_service.clients.ConnectionsClient;
import com.codingShuttle.linkedIn.notification_service.dto.PersonDto;
import com.codingShuttle.linkedIn.notification_service.entity.Notification;
import com.codingShuttle.linkedIn.notification_service.repository.NotificationRepository;
import com.codingShuttle.linkedIn.notification_service.service.SendNotification;
import com.codingShuttle.linkedIn.posts_service.events.PostCreatedEvents;
import com.codingShuttle.linkedIn.posts_service.events.PostLikeEvent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.notification_service.consumer
 * @since 30/10/2024 - 12:07 am
 */
@Service
    @Slf4j
    @RequiredArgsConstructor
    public class PoseServiceConsumer {

    private final ConnectionsClient connectionsClient;
    private final SendNotification sendNotification;


    private final NotificationRepository notificationRepository;

    public PoseServiceConsumer(ConnectionsClient connectionsClient, SendNotification sendNotification, NotificationRepository notificationRepository) {
        this.connectionsClient = connectionsClient;
        this.sendNotification = sendNotification;
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "post-created-topic")
    public void  handelPostCreated(String postCreatedEventsString){
        Gson gson = new Gson();
        org.slf4j.LoggerFactory.getLogger(PoseServiceConsumer.class).info("Sending the notification for the created POST: {}",postCreatedEventsString);
        try {
            PostCreatedEvents postCreatedEvents = gson.fromJson(postCreatedEventsString, PostCreatedEvents.class);
            List<PersonDto> connections = connectionsClient.getFirstDegreeConnection(postCreatedEvents.getCreatorId());
            for (PersonDto personDto: connections){
                sendNotification.send(personDto.getUserId(),"Your Connection"+postCreatedEvents.getCreatorId()+" has created a post check-it out ");
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }


    @KafkaListener(topics = "post-liked-topic")
    public void  handelPostLiked(String postLikeEventString){
        Gson gson=new Gson();
        org.slf4j.LoggerFactory.getLogger(PoseServiceConsumer.class).info("Sending the notification for the created POST: {}",postLikeEventString);
        try {
            PostLikeEvent postLikeEvent = gson.fromJson(postLikeEventString, PostLikeEvent.class);

            String message=String.format("Hey your %d post is liked by %d",postLikeEvent.getPostId(),postLikeEvent.getLikedByUserId());
            sendNotification.send(postLikeEvent.getCreatorId(),message);

        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }


}
