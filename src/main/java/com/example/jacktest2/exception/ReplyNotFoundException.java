package com.example.jacktest2.exception;

import javax.persistence.EntityNotFoundException;

public class ReplyNotFoundException extends EntityNotFoundException {

    private Throwable cause;

    public ReplyNotFoundException() {
        super();
    }

    public ReplyNotFoundException(String message) {
        super(message);
    }

    public ReplyNotFoundException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public ReplyNotFoundException(Throwable cause) {
        this.cause = cause;
    }
}
