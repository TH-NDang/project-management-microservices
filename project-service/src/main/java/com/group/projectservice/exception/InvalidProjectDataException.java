package com.group.projectservice.exception;

public class InvalidProjectDataException extends RuntimeException {
    public InvalidProjectDataException(String message) {
        super(message);
    }
}