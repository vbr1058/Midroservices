package com.codingShuttle.linkedIn.post_service.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.dto
 * @since 23/09/2024 - 01:10 am
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
}
