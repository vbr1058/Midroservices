package com.codingShuttle.linkedIn.user_service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.exception
 * @since 23/09/2024 - 11:13 pm
 */

@Data
public class ApiError {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError(String error,HttpStatus statusCode){
        this.timeStamp=LocalDateTime.now();
        this.error=error;
        this.statusCode=statusCode;
    }
}
