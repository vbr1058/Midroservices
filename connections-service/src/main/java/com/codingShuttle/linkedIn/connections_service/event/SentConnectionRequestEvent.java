package com.codingShuttle.linkedIn.connections_service.event;

import lombok.Builder;
import lombok.Data;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.connections_service.event
 * @since 31/10/2024 - 11:26 pm
 */
@Data
@Builder
public class SentConnectionRequestEvent {

    private Long senderId;
    private Long receiverId;

    @Override
    public String toString() {
        return "{" +
                "\"senderId\": " + senderId + ", " +
                "\"receiverId\": " + receiverId +
                "}";
    }

}
