package com.example.jacktest2.exception;

public class NoticeTitleAlreadyExistException extends RuntimeException {

    public NoticeTitleAlreadyExistException() {
        super();
    }

    public NoticeTitleAlreadyExistException(String message) {
        super(message);
    }

    public NoticeTitleAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoticeTitleAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
