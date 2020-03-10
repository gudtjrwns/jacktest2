package com.example.jacktest2.exception;

import javax.persistence.EntityNotFoundException;

public class NoticeNotFoundException extends EntityNotFoundException {

    private Throwable cause;

    public NoticeNotFoundException() {
        super();
    }

    public NoticeNotFoundException(String message) {
        super(message);
    }

    public NoticeNotFoundException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public NoticeNotFoundException(Throwable cause) {
        this.cause = cause;
    }
}
