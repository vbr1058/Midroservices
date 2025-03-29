package com.codingShuttle.linkedIn.post_service.exception;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.exception
 * @since 23/09/2024 - 11:16 pm
 */
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
