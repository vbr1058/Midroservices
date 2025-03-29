package com.codingShuttle.linkedIn.api_gatway.execption;

import com.codingShuttle.linkedIn.api_gatway.execption.DTO.ApiErrorApiFateWay;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.api_gatway.execption
 * @since 11/10/2024 - 11:55 pm
 */
@RestControllerAdvice
public class GlobalExceptionApiGateWay {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorApiFateWay> handleUnauthorizedException(UnauthorizedException exception) {
        ApiErrorApiFateWay apiError = new ApiErrorApiFateWay(exception.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorApiFateWay> handleGenericException(Exception exception) {
        ApiErrorApiFateWay apiError = new ApiErrorApiFateWay("An unexpected error occurred: " + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorApiFateWay> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiErrorApiFateWay apiError = new ApiErrorApiFateWay(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
