package com.example.xharktankspringboot.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("Could not find %s with id %d", resourceName, id));
    }

    public ResourceNotFoundException(String resourceName) {
        super(String.format("Could not find any %s with specified criteria", resourceName));
    }
}