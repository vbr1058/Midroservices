package com.codingShuttle.linkedIn.user_service.dto;

import lombok.Data;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.dto
 * @since 24/09/2024 - 03:08 pm
 */
@Data
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;

    public String getEmail() {
        return null;
    }
}
