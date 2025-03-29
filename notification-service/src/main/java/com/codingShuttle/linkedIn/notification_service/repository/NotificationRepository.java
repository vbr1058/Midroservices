package com.codingShuttle.linkedIn.notification_service.repository;

import com.codingShuttle.linkedIn.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.notification_service.repository
 * @since 30/10/2024 - 12:07 am
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
