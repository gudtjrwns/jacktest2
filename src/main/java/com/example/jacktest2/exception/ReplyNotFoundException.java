package com.example.jacktest2.exception;

public class ReplyNotFoundException extends EntityNotFoundException {

    public ReplyNotFoundException() {
        super();
    }

    public ReplyNotFoundException(String message) {
        super(message);
    }

    public ReplyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReplyNotFoundException(Throwable cause) {
        super(cause);
    }
}
