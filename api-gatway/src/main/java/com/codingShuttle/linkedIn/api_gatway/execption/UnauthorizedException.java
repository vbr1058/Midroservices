package com.codingShuttle.linkedIn.api_gatway.execption;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.api_gatway.execption
 * @since 11/10/2024 - 11:56 pm
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}