package com.codingShuttle.linkedIn.notification_service.service;

import com.codingShuttle.linkedIn.notification_service.entity.Notification;
import com.codingShuttle.linkedIn.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.notification_service.service
 * @since 31/10/2024 - 11:43 pm
 */
@Service
@RequiredArgsConstructor
public class SendNotification {


    private final NotificationRepository notificationRepository;

    //Send Notification method
    public void send(Long userId, String message){
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUserId(userId);

        notificationRepository.save(notification);

    }
}
