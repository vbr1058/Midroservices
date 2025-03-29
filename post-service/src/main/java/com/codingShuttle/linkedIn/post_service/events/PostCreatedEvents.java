package com.codingShuttle.linkedIn.post_service.events;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.events
 * @since 28/10/2024 - 12:20 am
 */
@Data
@Builder
public class PostCreatedEvents {
    Long creatorId;
    String content;
    Long postId;

    @Override
    public String toString() {
        return "{" +
                "\"creatorId\":" + creatorId +
                ", \"content\":\"" + content + "\"" +
                ", \"postId\":" + postId +
                '}';
    }
}
