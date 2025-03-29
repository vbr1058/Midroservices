package com.codingShuttle.linkedIn.api_gatway.execption.DTO;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.api_gatway.execption.DTO
 * @since 12/10/2024 - 12:00 am
 */
@Data
public class ApiErrorApiFateWay {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;

    public ApiErrorApiFateWay(String error,HttpStatus statusCode){
        this.timeStamp=LocalDateTime.now();
        this.error=error;
        this.statusCode=statusCode;
    }
}