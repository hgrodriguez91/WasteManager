package com.technical.test.wastemanager.exception;

public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified error message.
     * This exception is thrown when a resource is not found or does not exist.
     *
     * @param message The error message describing the exception.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
