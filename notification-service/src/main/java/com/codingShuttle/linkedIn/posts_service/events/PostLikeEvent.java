package com.codingShuttle.linkedIn.posts_service.events;

import lombok.Builder;
import lombok.Data;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.entity
 * @since 29/10/2024 - 12:00 am
 */

@Data
@Builder
public class PostLikeEvent {

    Long postId;
    Long creatorId;
    Long likedByUserId;

    @Override
    public String toString() {
        return "{" +
                "\"postId\":" + postId +
                ", \"creatorId\":" + creatorId +
                ", \"likedByUserId\":" + likedByUserId +
                '}';
    }

}
