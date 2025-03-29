package com.codingShuttle.linkedIn.post_service.exception;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.exception
 * @since 23/09/2024 - 11:12 pm
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

}
