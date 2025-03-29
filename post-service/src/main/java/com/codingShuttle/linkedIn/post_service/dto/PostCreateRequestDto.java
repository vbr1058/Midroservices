package com.codingShuttle.linkedIn.post_service.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.dto
 * @since 23/09/2024 - 01:12 am
 */
@Data
public class PostCreateRequestDto {
    private String content;
}
