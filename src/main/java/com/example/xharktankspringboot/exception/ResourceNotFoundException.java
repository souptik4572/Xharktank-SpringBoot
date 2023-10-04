package com.example.xharktankspringboot.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("Could not find %s with id %d", resourceName, id));
    }
}