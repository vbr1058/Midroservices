package com.codingShuttle.linkedIn.user_service.dto;

import lombok.Data;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.dto
 * @since 24/09/2024 - 03:07 pm
 */

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
