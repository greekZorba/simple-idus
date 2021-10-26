package com.homework.simpleidus.api.exception;

public class ExistException extends RuntimeException {
    public ExistException() {
        super();
    }

    public ExistException(String message) {
        super(message);
    }
}
