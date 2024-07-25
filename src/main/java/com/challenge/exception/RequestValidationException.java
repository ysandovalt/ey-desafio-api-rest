package com.challenge.exception;

/**
 *
 * @author ysand
 */
public class RequestValidationException extends RuntimeException {

    public RequestValidationException(String message) {
        super(message);
    }
}
