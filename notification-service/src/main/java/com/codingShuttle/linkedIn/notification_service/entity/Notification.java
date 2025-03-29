package com.codingShuttle.linkedIn.notification_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.notification_service.entity
 * @since 30/10/2024 - 12:05 am
 */

@Entity(name = "notification_details")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long userId; //this is the
    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
