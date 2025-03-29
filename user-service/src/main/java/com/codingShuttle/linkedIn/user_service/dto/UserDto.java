package com.codingShuttle.linkedIn.user_service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.dto
 * @since 24/09/2024 - 03:07 pm
 */
@Data
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
}
